package com.nick.spinosa.raidtransactions.entities

import javax.persistence.*

enum class Transgression {
    AFK_NO_WARNING,
    PULLED_BOSS,
    PULLED_ADDS,
    FAILED_LEARNED_MECHANIC,
    AFK_TOO_LONG,
    OPEN_MIC,
    WAS_A_DICK,
    NO_RAID_MATS,
    LATE_FOR_RAID,
    DIDNT_REPAIR
}

@Entity
data class Amount(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val gold: Long,
        val silver: Long,
        val copper: Long
)

@Entity
data class Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        @OneToOne
        val raid: Raid,
        @OneToOne
        val raider: Raider,
        @OneToOne
        val cost: Amount,
        val reason: Transgression,
        val detail: String?
)