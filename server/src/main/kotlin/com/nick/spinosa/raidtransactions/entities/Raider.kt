package com.nick.spinosa.raidtransactions.entities

import javax.persistence.*

@Entity
@Table(name="Raiders")
class Raider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(unique = true)
    lateinit var name: String
}