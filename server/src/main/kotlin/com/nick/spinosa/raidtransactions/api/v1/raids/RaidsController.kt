package com.nick.spinosa.raidtransactions.api.v1.raiders

import org.springframework.beans.factory.annotation.Autowired
import com.nick.spinosa.raidtransactions.daos.RaidDao
import com.nick.spinosa.raidtransactions.entities.Raid
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/raids")
class RaidsController() {

    val PAGE_SIZE: Int = 10

    @Autowired
    lateinit var raidDao: RaidDao

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): Raid {
        return raidDao.findById(id).get()
    }

    @PostMapping()
    fun createOrUpdate(@RequestBody raid: Raid) {
        raidDao.save(raid)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        raidDao.deleteById(id)
    }

    @GetMapping()
    fun getAll(@RequestParam(name = "page", defaultValue = "1") pageNumber: Int): List<Raid> {
        return raidDao.findAll(PageRequest.of(
                pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "instance")).content
    }
}