package com.nick.spinosa.raidtransactions.daos

import com.nick.spinosa.raidtransactions.entities.Raid
import org.springframework.data.repository.CrudRepository

interface RaidDao: CrudRepository<Raid, Long> {

}