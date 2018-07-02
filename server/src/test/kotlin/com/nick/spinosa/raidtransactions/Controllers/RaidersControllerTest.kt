package com.nick.spinosa.raidtransactions.Controllers

import com.nick.spinosa.raidtransactions.entities.Raider
import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RaidersController {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testCreate() {
        val raider1 = Raider()
        raider1.name = "raider1"
        testRestTemplate.postForEntity<Raider>("/api/v1/raiders", raider1, Raider::class)

        val result = testRestTemplate.exchange(RequestEntity<List<Raider>>(HttpMethod.GET, URI("/api/v1/raiders")), typeRef<List<Raider>>())
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertTrue(result.body!!.map(Raider::name).contains(raider1.name))
    }

}

inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}