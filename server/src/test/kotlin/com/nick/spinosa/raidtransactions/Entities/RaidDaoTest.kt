package com.nick.spinosa.raidtransactions.Entities

import com.nick.spinosa.raidtransactions.daos.RaidDao
import com.nick.spinosa.raidtransactions.entities.Instance
import com.nick.spinosa.raidtransactions.entities.Raid
import com.nick.spinosa.raidtransactions.entities.Raider
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.sql.Timestamp
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
class RaidDaoTest {

    @Autowired
    lateinit var raidDao: RaidDao

    @Test
    fun testEntity() {
        val raid1 = setUpRaid("testRaidLeader")
        raidDao.save(raid1)

        val result = raidDao.findAll().toList()
        assertTrue(result.map(Raid::raidLeader).contains(raid1.raidLeader))
    }

    @Test
    fun testFind() {
        val raid1 = setUpRaid("testRaidLeader")
        raidDao.save(raid1)

        val result = raidDao.findById(raid1.id!!).orElse(null)
        assertTrue(result != null)
        assertTrue(result.raidLeader == raid1.raidLeader)
    }

    @Test
    fun testDelete() {
        val raid1 = setUpRaid("testRaidLeader")
        val raid = raidDao.save(raid1)
        raidDao.deleteById(raid.id!!)

        val result = raidDao.findById(raid.id!!).orElse(null)
        assertTrue(result == null)
    }

    @Test
    fun testGetAll() {
        val raid1 = setUpRaid("testRaidLeader1")
        raidDao.save(raid1)

        val raid2 = setUpRaid("testRaidLeader2")
        raidDao.save(raid2)

        val result = raidDao.findAll().toList()
        assertTrue(result.map(Raid::raidLeader).contains(raid1.raidLeader)
                && result.map(Raid::raidLeader).contains(raid2.raidLeader))
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