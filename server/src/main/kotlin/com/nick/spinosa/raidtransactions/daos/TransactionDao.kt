package com.nick.spinosa.raidtransactions.daos

import com.nick.spinosa.raidtransactions.entities.Instance
import com.nick.spinosa.raidtransactions.entities.Transaction
import com.nick.spinosa.raidtransactions.entities.Transgression
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
interface TransactionDao: PagingAndSortingRepository<Transaction, Long> {

    @Query("select T from Transaction T where" +
            "(:raiderId = null OR T.raider.id = :raiderId) AND" +
            "(:instance = null OR T.raid.instance = :instance) AND" +
            "(:transgression = null OR T.reason = :transgression) AND" +
            "(:date = null OR T.raid.date = :date) AND" +
            "(:gold = null OR T.cost.gold = :gold) AND" +
            "(:silver = null OR T.cost.silver = :silver) AND" +
            "(:copper = null OR T.cost.copper = :copper)")
    fun findAllById(@Param("raiderId") raiderId: Long?,
                    @Param("instance") instance: Instance?,
                    @Param("transgression") transgression: Transgression?,
                    @Param("date") date: Timestamp?,
                    @Param("gold") gold: Long?,
                    @Param("silver") silver: Long?,
                    @Param("copper") copper: Long?,
                    pageable: Pageable): Page<Transaction>

}