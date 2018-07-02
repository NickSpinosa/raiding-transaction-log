package com.nick.spinosa.raidtransactions.daos

import com.nick.spinosa.raidtransactions.entities.Transaction
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionDao: PagingAndSortingRepository<Transaction, Long> {

}