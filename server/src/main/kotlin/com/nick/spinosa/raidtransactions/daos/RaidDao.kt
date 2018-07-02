package com.nick.spinosa.raidtransactions.daos

import com.nick.spinosa.raidtransactions.entities.Raid
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface RaidDao: PagingAndSortingRepository<Raid, Long> {

}