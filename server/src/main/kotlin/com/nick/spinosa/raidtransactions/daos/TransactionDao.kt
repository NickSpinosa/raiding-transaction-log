package com.nick.spinosa.raidtransactions.daos

import com.nick.spinosa.raidtransactions.entities.Transaction
import org.springframework.data.repository.CrudRepository

interface TransactionDao: CrudRepository<Transaction, Long> {
}