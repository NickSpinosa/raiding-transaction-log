package com.nick.spinosa.raidtransactions.Entities

import com.nick.spinosa.raidtransactions.daos.RaiderDao
import com.nick.spinosa.raidtransactions.entities.Raider
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class RaiderDaoTest {

    @Autowired
    lateinit var raiderDao: RaiderDao

    @Test
    fun testEntity() {
        val raider1 = setUpRaider("testRaider1")
        raiderDao.save(raider1)

        val result = raiderDao.findAll().toList()
        assertTrue(result.map(Raider::name).contains(raider1.name))
    }

    @Test
    fun testFind() {
        val raider1 = setUpRaider("testRaider1")
        val raider = raiderDao.save(raider1)

        val result = raiderDao.findById(raider.id!!).orElse(null)
        assertTrue(result != null)
        assertTrue(result.name == raider1.name)
    }

    @Test
    fun testDelete() {
        val raider1 = setUpRaider("testRaider1")
        val raider = raiderDao.save(raider1)
        raiderDao.deleteById(raider.id!!)

        val result = raiderDao.findById(raider.id!!).orElse(null)
        assertTrue(result == null)
    }

    @Test
    fun testGetAll() {
        val raider1 = setUpRaider("testRaider1")
        raiderDao.save(raider1)

        val raider2 = setUpRaider("testRaider2")
        raiderDao.save(raider2)

        val result = raiderDao.findAll().toList()
        assertTrue(result.map(Raider::name).contains(raider1.name)
        && result.map(Raider::name).contains(raider2.name))
    }

    fun setUpRaider(name: String): Raider {
        val raider = Raider()
        raider.name = name

        return raider
    }
}