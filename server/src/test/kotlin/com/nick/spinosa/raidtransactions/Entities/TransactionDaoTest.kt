package com.nick.spinosa.raidtransactions.Entities

import com.nick.spinosa.raidtransactions.daos.TransactionDao
import com.nick.spinosa.raidtransactions.entities.*
import org.junit.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
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

    fun setUpTransaction(name: String): Transaction {
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"))

        var raider = Raider()
        raider.name = "testRaider$name"

        var raidLeader = Raider()
        raidLeader.name = "testRaidLeader$name"

        var raid = Raid()
        raid.instance = Instance.BWL_10
        raid.date = Timestamp(calendar.timeInMillis)
        raid.raidLeader = raidLeader

        var amount = Amount()
        amount.gold = 50
        amount.silver = 50
        amount.copper = 50

        var transaction = Transaction()
        transaction.raid = raid
        transaction.raider = raider
        transaction.cost = amount
        transaction.reason = Transgression.AFK_NO_WARNING
        transaction.detail = "testing$name"

        return transaction
    }
}