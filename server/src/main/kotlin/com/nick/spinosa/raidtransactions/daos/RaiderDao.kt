package com.nick.spinosa.raidtransactions.daos

import com.nick.spinosa.raidtransactions.entities.Raider
import org.springframework.data.repository.CrudRepository

interface RaiderDao: CrudRepository<Raider, Long> {
}