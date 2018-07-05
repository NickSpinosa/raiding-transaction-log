package com.nick.spinosa.raidtransactions.Entities

import com.nick.spinosa.raidtransactions.entities.*
import java.sql.Timestamp
import java.util.*

fun setUpTransaction(name: String): Transaction {
    val raider = setUpRaider("testRaider$name")

    val raid = setUpRaid("testRaidLeader$name")

    val amount = Amount()
    amount.gold = 50
    amount.silver = 50
    amount.copper = 50

    val transaction = Transaction()
    transaction.raid = raid
    transaction.raider = raider
    transaction.cost = amount
    transaction.reason = Transgression.AFK_NO_WARNING
    transaction.detail = "testing$name"

    return transaction
}

fun setUpRaid(name: String): Raid {
    val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"))

    val raidLeader = setUpRaider(name)

    val raid = Raid()
    raid.instance = Instance.BWL_10
    raid.date = Timestamp(calendar.timeInMillis)
    raid.raidLeader = raidLeader

    return raid
}

fun setUpRaider(name: String): Raider {
    val raider = Raider()
    raider.name = name

    return raider
}