package com.nick.spinosa.raidtransactions.api.v1.transactions

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import com.nick.spinosa.raidtransactions.daos.TransactionDao
import com.nick.spinosa.raidtransactions.entities.Transaction
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/transactions")
class TransactionsController() {

    @Autowired
    val transactionDao: TransactionDao? = null

    @GetMapping("/{id}")
    fun read(id: Long) {
        transactionDao!!.findById(id)
    }

    @PostMapping()
    fun createOrUpdate(transaction: Transaction) {
        transactionDao!!.save(transaction)
    }

    @DeleteMapping("/{id}")
    fun delete(id: Long) {
        transactionDao!!.deleteById(id)
    }

    @GetMapping()
    fun getAll() {
        transactionDao!!.findAll()
    }
}