package com.nick.spinosa.raidtransactions.Controllers

import com.nick.spinosa.raidtransactions.Entities.setUpTransaction
import com.nick.spinosa.raidtransactions.entities.*
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
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionsControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testCreate() {
        val transaction1 = setUpTransaction("TransactionsCreate")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction1, Transaction::class)

        val transactions = testRestTemplate.exchange(RequestEntity<List<Transaction>>(HttpMethod.GET, URI("/api/v1/transactions")), typeRef<List<Transaction>>())
        assertNotNull(transactions)
        assertEquals(transactions.statusCode, HttpStatus.OK)
        assertTrue(transactions.body!!.map(Transaction::raid).map(Raid::raidLeader).map(Raider::name).contains(transaction1.raid.raidLeader.name))
    }

    @Test
    fun testRead() {
        val transaction1 = setUpTransaction("TransactionsRead")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction1, Transaction::class)

        val transactions = testRestTemplate.exchange(RequestEntity<List<Transaction>>(HttpMethod.GET, URI("/api/v1/transactions")), typeRef<List<Transaction>>())
        assertNotNull(transactions)

        val transaction1Id = transactions.body!!.filter { transaction -> transaction.raid.raidLeader.name == transaction1.raid.raidLeader.name }.first().id
        val transaction = testRestTemplate.getForEntity<Transaction>("/api/v1/transactions/$transaction1Id")
        assertNotNull(transaction)
        assertEquals(transactions.statusCode, HttpStatus.OK)
        assertTrue(transaction.body!!.raid.raidLeader.name == transaction1.raid.raidLeader.name)
    }

    @Test
    fun testDelete() {
        val transaction1 = setUpTransaction("TransactionsDelete")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction1, Transaction::class)

        var transactions = testRestTemplate.exchange(RequestEntity<List<Transaction>>(HttpMethod.GET, URI("/api/v1/transactions")), typeRef<List<Transaction>>())
        assertNotNull(transactions.body)

        val transaction1Id = transactions.body!!.filter { transaction -> transaction.raid.raidLeader.name == transaction1.raid.raidLeader.name }.first().id
        testRestTemplate.delete("/api/v1/transactions/$transaction1Id")
        transactions = testRestTemplate.exchange(RequestEntity<List<Transaction>>(HttpMethod.GET, URI("/api/v1/transactions")), typeRef<List<Transaction>>())
        assertFalse(transactions.body!!.map(Transaction::raid).map(Raid::raidLeader).map(Raider::name).contains(transaction1.raid.raidLeader.name))
    }

    @Test
    fun testFindAll() {
        val transaction1 = setUpTransaction("TransactionsFind1")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction1, Transaction::class)

        val transaction2 = setUpTransaction("TransactionsFind2")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction2, Transaction::class)

        val transactions = testRestTemplate.exchange(RequestEntity<List<Transaction>>(HttpMethod.GET, URI("/api/v1/transactions")), typeRef<List<Transaction>>())
        assertNotNull(transactions.body)
        assertTrue(transactions.body!!.map(Transaction::raid).map(Raid::raidLeader).map(Raider::name).contains(transaction1.raid.raidLeader.name)
                && transactions.body!!.map(Transaction::raid).map(Raid::raidLeader).map(Raider::name).contains(transaction2.raid.raidLeader.name))
    }

    @Test
    fun testPages() {
        val transaction1 = setUpTransaction("testRaidLeaderTransactionsPages1")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction1, Transaction::class)

        val transaction2 = setUpTransaction("testRaidLeaderTransactionsPages2")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction2, Transaction::class)

        val transaction3 = setUpTransaction("testRaidLeaderTransactionsPages3")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction3, Transaction::class)

        val transactions = testRestTemplate.exchange(RequestEntity<List<Transaction>>(HttpMethod.GET, URI("/api/v1/transactions?page-size=2")), typeRef<List<Transaction>>())
        assertTrue(transactions.body!!.size <= 2)
    }

    @Test
    fun testPageDefault() {
        val transaction1 = setUpTransaction("testRaidLeaderTransactionsPagesDefault1")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction1, Transaction::class)

        val transaction2 = setUpTransaction("testRaidLeaderTransactionsPagesDefault2")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction2, Transaction::class)

        val transaction3 = setUpTransaction("testRaidLeaderTransactionsPagesDefault3")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction3, Transaction::class)

        val transaction4 = setUpTransaction("testRaidLeaderTransactionsPagesDefault4")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction4, Transaction::class)

        val transaction5 = setUpTransaction("testRaidLeaderTransactionsPagesDefault5")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction5, Transaction::class)

        val transaction6 = setUpTransaction("testRaidLeaderTransactionsPagesDefault6")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction6, Transaction::class)

        val transaction7 = setUpTransaction("testRaidLeaderTransactionsPagesDefault7")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction7, Transaction::class)

        val transaction8 = setUpTransaction("testRaidLeaderTransactionsPagesDefault8")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction8, Transaction::class)

        val transaction9 = setUpTransaction("testRaidLeaderTransactionsPagesDefault9")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction9, Transaction::class)

        val transaction10 = setUpTransaction("testRaidLeaderTransactionsPagesDefault10")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction10, Transaction::class)

        val transaction11 = setUpTransaction("testRaidLeaderTransactionsPagesDefault11")
        testRestTemplate.postForEntity<Transaction>("/api/v1/transactions", transaction11, Transaction::class)

        val transactions = testRestTemplate.exchange(RequestEntity<List<Transaction>>(HttpMethod.GET, URI("/api/v1/transactions")), typeRef<List<Transaction>>())
        assertTrue(transactions.body!!.size == 10)
    }
}