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
@Table(name="Amounts")
class Amount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var gold: Long? = null
    var silver: Long? = null
    var copper: Long? = null
}

@Entity
@Table(name="Transactions")
class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    @OneToOne(cascade = [CascadeType.PERSIST])
    lateinit var raid: Raid
    @OneToOne(cascade = [CascadeType.PERSIST])
    lateinit var raider: Raider
    @OneToOne(cascade = [CascadeType.ALL])
    lateinit var cost: Amount
    lateinit var reason: Transgression
    lateinit var detail: String
}