package com.nick.spinosa.raidtransactions.Controllers

import com.nick.spinosa.raidtransactions.entities.Instance
import com.nick.spinosa.raidtransactions.entities.Raid
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
import java.sql.Timestamp
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RaidsControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testCreate() {
        val raid1 = setUpRaid("testRaidLeaderCreate")
        testRestTemplate.postForEntity<Raid>("/api/v1/raids", raid1, Raid::class)

        val raids = testRestTemplate.exchange(RequestEntity<List<Raid>>(HttpMethod.GET, URI("/api/v1/raids")), typeRef<List<Raid>>())
        assertNotNull(raids)
        assertEquals(raids.statusCode, HttpStatus.OK)
        assertTrue(raids.body!!.map(Raid::raidLeader).map(Raider::name).contains(raid1.raidLeader.name))
    }

    @Test
    fun testRead() {
        val raid1 = setUpRaid("testRaidLeaderRaidsRead")
        testRestTemplate.postForEntity<Raid>("/api/v1/raids", raid1, Raid::class)

        val raids = testRestTemplate.exchange(RequestEntity<List<Raid>>(HttpMethod.GET, URI("/api/v1/raids")), typeRef<List<Raid>>())
        assertNotNull(raids)

        val raid1Id = raids.body!!.map(Raid::id)[0]
        val raid = testRestTemplate.getForEntity<Raid>("/api/v1/raids/$raid1Id")
        assertNotNull(raid)
        assertEquals(raids.statusCode, HttpStatus.OK)
        assertTrue(raid.body!!.raidLeader.name == raid1.raidLeader.name)
    }

    @Test
    fun testDelete() {
        val raid1 = setUpRaid("testRaidLeaderRaidsDelete")
        testRestTemplate.postForEntity<Raid>("/api/v1/raids", raid1, Raid::class)

        var raids = testRestTemplate.exchange(RequestEntity<List<Raid>>(HttpMethod.GET, URI("/api/v1/raids")), typeRef<List<Raid>>())
        assert(raids.body!!.isNotEmpty())

        val raid1Id = raids.body!!.filter { raid -> raid.raidLeader.name == raid1.raidLeader.name }.first().id
        testRestTemplate.delete("/api/v1/raids/$raid1Id")
        raids = testRestTemplate.exchange(RequestEntity<List<Raid>>(HttpMethod.GET, URI("/api/v1/raids")), typeRef<List<Raid>>())
        assertFalse(raids.body!!.map(Raid::raidLeader).map(Raider::name).contains(raid1.raidLeader.name))
    }

    @Test
    fun testFindAll() {
        val raid1 = setUpRaid("testRaidLeaderRaidsFind1")
        testRestTemplate.postForEntity<Raid>("/api/v1/raids", raid1, Raid::class)

        val raid2 = setUpRaid("testRaidLeaderRaidsFind2")
        testRestTemplate.postForEntity<Raid>("/api/v1/raids", raid2, Raid::class)

        val raids = testRestTemplate.exchange(RequestEntity<List<Raid>>(HttpMethod.GET, URI("/api/v1/raids")), typeRef<List<Raid>>())
        assertNotNull(raids.body)
        assertTrue(raids.body!!.map(Raid::raidLeader).map(Raider::name).contains(raid1.raidLeader.name)
                && raids.body!!.map(Raid::raidLeader).map(Raider::name).contains(raid2.raidLeader.name))
    }

    fun setUpRaid(name: String): Raid {
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"))

        val raidLeader = Raider()
        raidLeader.name = name

        val raid = Raid()
        raid.instance = Instance.BWL_10
        raid.date = Timestamp(calendar.timeInMillis)
        raid.raidLeader = raidLeader

        return raid
    }
}
