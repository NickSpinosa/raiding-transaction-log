package com.nick.spinosa.raidtransactions.entities

import javax.persistence.*

@Entity
data class Raider (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(unique = true)
        var name: String
)