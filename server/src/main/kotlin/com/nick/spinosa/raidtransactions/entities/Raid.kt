package com.nick.spinosa.raidtransactions.entities

import java.sql.Timestamp
import javax.persistence.*

enum class Instance {
    RUINS_10,
    RUINS_25,
    TEMPLE_10,
    TEMPLE_25,
    MC_10,
    MC_25,
    ZG_10,
    ZG_25,
    BWL_10,
    BWL_25,
    ONY_10,
    ONY_25
}

@Entity
@Table(name="Raids")
class Raid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    lateinit var instance: Instance
    lateinit var date: Timestamp
    @OneToOne(cascade = [CascadeType.PERSIST])
    lateinit var raidLeader: Raider
}