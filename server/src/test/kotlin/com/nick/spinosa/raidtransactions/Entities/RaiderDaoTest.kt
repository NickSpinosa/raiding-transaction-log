package com.nick.spinosa.raidtransactions.Entities

import com.nick.spinosa.raidtransactions.Controllers.typeRef
import com.nick.spinosa.raidtransactions.daos.RaiderDao
import com.nick.spinosa.raidtransactions.entities.Raider
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@DataJpaTest
class Raider {

    @Autowired
    lateinit var raiderDao: RaiderDao

    @Test
    fun testEntity() {
        var raider1 = Raider()
        raider1.name = "testRaider1"

        raiderDao.save(raider1)

        val result = raiderDao.findAll().toList()
        assertTrue(result.map(Raider::name).contains(raider1.name))
    }
}