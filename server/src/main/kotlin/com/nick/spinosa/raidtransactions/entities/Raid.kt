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
data class Raid(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val instance: Instance,
        val date: Timestamp,
        @OneToOne
        val raidLeader: Raider
)