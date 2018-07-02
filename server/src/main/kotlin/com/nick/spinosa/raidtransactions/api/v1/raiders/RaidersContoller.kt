package com.nick.spinosa.raidtransactions.api.v1.raiders

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import com.nick.spinosa.raidtransactions.daos.RaiderDao
import com.nick.spinosa.raidtransactions.entities.Raider
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/raiders")
class RaidersController() {

    @Autowired
    val raiderDao: RaiderDao? = null

    @GetMapping("/1")
    fun search(id: Long) {
        raiderDao!!.findById(id)
    }

    @PostMapping("/2")
    fun createOrUpdate(raider: Raider) {
        raiderDao!!.save(raider)
    }

    @DeleteMapping("/3")
    fun delete(id: Long) {
        raiderDao!!.deleteById(id)
    }

    @GetMapping("/4")
    fun getAll() {
        raiderDao!!.findAll()
    }
}