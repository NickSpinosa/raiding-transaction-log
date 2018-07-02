package com.nick.spinosa.raidtransactions.Controllers

import com.nick.spinosa.raidtransactions.entities.Raider
import com.nick.spinosa.raidtransactions.typeRef
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RaidersControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testCreate() {
        val raider1 = setUpRaider("testRaiderRaidersCreate")
        testRestTemplate.postForEntity<Raider>("/api/v1/raiders", raider1, Raider::class)

        val result = testRestTemplate.exchange(RequestEntity<List<Raider>>(HttpMethod.GET, URI("/api/v1/raiders")), typeRef<List<Raider>>())
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertTrue(result.body!!.map(Raider::name).contains(raider1.name))
    }

    @Test
    fun testRead() {
        val raider1 = setUpRaider("testRaiderRaidersRead")
        testRestTemplate.postForEntity<Raider>("/api/v1/raiders", raider1, Raider::class)

        val raiders = testRestTemplate.exchange(RequestEntity<List<Raider>>(HttpMethod.GET, URI("/api/v1/raiders")), typeRef<List<Raider>>())
        assertNotNull(raiders)

        val raider1Id = raiders.body!!.filter { raider -> raider.name == raider1.name }.first().id
        val raider = testRestTemplate.getForEntity<Raider>("/api/v1/raiders/$raider1Id")
        assertNotNull(raider)
        assertEquals(raiders.statusCode, HttpStatus.OK)
        assertTrue(raider.body!!.name == raider1.name)
    }

    @Test
    fun testDelete() {
        val raider1 = setUpRaider("testRaiderRaidersDelete")
        testRestTemplate.postForEntity<Raider>("/api/v1/raiders", raider1, Raider::class)

        var raiders = testRestTemplate.exchange(RequestEntity<List<Raider>>(HttpMethod.GET, URI("/api/v1/raiders")), typeRef<List<Raider>>())
        assertNotNull(raiders.body)

        val raider1Id = raiders.body!!.filter { raider -> raider.name == raider1.name }.first().id
        testRestTemplate.delete("/api/v1/raiders/$raider1Id")
        raiders = testRestTemplate.exchange(RequestEntity<List<Raider>>(HttpMethod.GET, URI("/api/v1/raiders")), typeRef<List<Raider>>())
        assertFalse(raiders.body!!.map(Raider::name).contains(raider1.name))
    }

    @Test
    fun testFindAll() {
        val raider1 = setUpRaider("testRaiderRaidersFind1")
        testRestTemplate.postForEntity<Raider>("/api/v1/raiders", raider1, Raider::class)

        val raider2 = setUpRaider("testRaiderRaidersFind2")
        testRestTemplate.postForEntity<Raider>("/api/v1/raiders", raider2, Raider::class)

        var raiders = testRestTemplate.exchange(RequestEntity<List<Raider>>(HttpMethod.GET, URI("/api/v1/raiders")), typeRef<List<Raider>>())
        assertNotNull(raiders.body)
        assertTrue(raiders.body!!.map(Raider::name).contains(raider1.name)
        && raiders.body!!.map(Raider::name).contains(raider2.name))
    }

    fun setUpRaider(name: String): Raider {
        val raider = Raider()
        raider.name = name

        return raider
    }
}