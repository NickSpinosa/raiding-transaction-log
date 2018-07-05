package com.nick.spinosa.raidtransactions.api.v1.raiders

import org.springframework.beans.factory.annotation.Autowired
import com.nick.spinosa.raidtransactions.daos.RaiderDao
import com.nick.spinosa.raidtransactions.entities.Raider
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/raiders")
class RaidersController() {
    @Autowired
    lateinit var raiderDao: RaiderDao

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): Raider {
        return raiderDao.findById(id).get()
    }

    @PostMapping()
    fun createOrUpdate(@RequestBody raider: Raider) {
        raiderDao.save(raider)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        raiderDao.deleteById(id)
    }

    @GetMapping()
    fun getAll(@RequestParam(name = "page", defaultValue = "1") pageNumber: Int, @RequestParam(name = "page-size", defaultValue = "10") pageSize: Int): List<Raider> {

        return raiderDao.findAll(PageRequest.of(
                pageNumber - 1, pageSize, Sort.Direction.ASC, "name")).content
    }
}
//testing 123