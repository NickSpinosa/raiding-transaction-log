package com.nick.spinosa.raidtransactions.Entities

import com.nick.spinosa.raidtransactions.daos.TransactionDao
import com.nick.spinosa.raidtransactions.entities.*
import org.junit.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.junit4.SpringRunner
import java.sql.Timestamp
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
class TransactionDaoTest {

    @Autowired
    lateinit var transactionDao: TransactionDao

    @Test
    fun testEntity() {
        val transaction1 = setUpTransaction("1")
        transactionDao.save(transaction1)

        val result = transactionDao.findAll().toList()
        assertTrue(result.map(Transaction::raider).contains(transaction1.raider))
    }

    @Test
    fun testFind() {
        val transaction1 = setUpTransaction("1")
        val transaction = transactionDao.save(transaction1)

        val result = transactionDao.findById(transaction.id!!).orElse(null)
        Assert.assertTrue(result != null)
        Assert.assertTrue(result.reason == transaction1.reason)
    }

    @Test
    fun testDelete() {
        val transaction1 = setUpTransaction("1")
        val transaction = transactionDao.save(transaction1)
        transactionDao.deleteById(transaction.id!!)

        val result = transactionDao.findById(transaction.id!!).orElse(null)
        Assert.assertTrue(result == null)
    }

    @Test
    fun testGetAll() {
        val transaction1 = setUpTransaction("1")
        transactionDao.save(transaction1)

        val transaction2 = setUpTransaction("2")
        transactionDao.save(transaction2)

        val result = transactionDao.findAll().toList()
        Assert.assertTrue(result.map(Transaction::detail).contains(transaction1.detail)
                && result.map(Transaction::detail).contains(transaction2.detail))
    }

    @Test
    fun testRaiderFilter() {
        val transaction1 = setUpTransaction("1")
        val savet1 = transactionDao.save(transaction1)

        val raider = setUpRaider("testRaiderFilter")

        val raid = Raid()
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"))
        val raidLeader = setUpRaider("testRaidLeaderFilter")
        raid.instance = Instance.MC_10
        raid.date = Timestamp(calendar.timeInMillis)
        raid.raidLeader = raidLeader

        val amount = Amount()
        amount.gold = 50
        amount.silver = 50
        amount.copper = 50

        val transaction2 = Transaction()
        transaction2.raid = raid
        transaction2.raider = raider
        transaction2.cost = amount
        transaction2.reason = Transgression.FAILED_LEARNED_MECHANIC
        transaction2.detail = "testingfilters"
        val savet2 = transactionDao.save(transaction2)

        val transaction3 = setUpTransaction("2")
        val savet3 = transactionDao.save(transaction3)

        val pageSize = 1
        val pageNum = 1

        val result = transactionDao.findAllById(null, Instance.BWL_10, null,
                null, null, null, null,
                PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "raider"))
        assertTrue(result.map(Transaction::raid).map(Raid::instance).contains(savet1.raid.instance) &&
                !result.map(Transaction::raid).map(Raid::instance).contains(savet2.raid.instance))
        assertTrue(result.size == 1)
    }
}