package com.nick.spinosa.raidtransactions.daos

import com.nick.spinosa.raidtransactions.entities.Raider
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository

@Repository
interface RaiderDao: PagingAndSortingRepository<Raider, Long> {

}