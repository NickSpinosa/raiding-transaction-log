package com.nick.spinosa.raidtransactions.api.v1.transactions

import org.springframework.beans.factory.annotation.Autowired
import com.nick.spinosa.raidtransactions.daos.TransactionDao
import com.nick.spinosa.raidtransactions.entities.Transaction
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionsController() {

    @Autowired
    lateinit var transactionDao: TransactionDao

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): Transaction {
        return transactionDao.findById(id).get()
    }

    @PostMapping()
    fun createOrUpdate(@RequestBody raider: Transaction) {
        transactionDao.save(raider)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        transactionDao.deleteById(id)
    }

    @GetMapping()
    fun getAll(@RequestParam(name = "page", defaultValue = "1") pageNumber: Int, @RequestParam(name = "page-size", defaultValue = "10") pageSize: Int): List<Transaction> {
        return transactionDao.findAll(PageRequest.of(
                pageNumber - 1, pageSize, Sort.Direction.ASC, "raider")).content
    }
}