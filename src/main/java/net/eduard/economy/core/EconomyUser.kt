package net.eduard.economy.core

import net.eduard.api.lib.database.annotations.*
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.economy.EduEconomy

@TableName("economy_users")
class EconomyUser {
    @ColumnPrimary
    var id = 0

    @ColumnUnique
    var player = FakePlayer("Eduard")

    val name get() = player.name

    var amount = 0.0
    set(value) {
        field=value
        EduEconomy.instance.sqlManager.updateDataQueue(this);
    }
    var bonus = 0.0
    var discont = 0.0

    fun transaction(reason : String , amount : Double): EconomyTransaction {
        val transaction = EconomyTransaction()
        transaction.user= this
        transaction.reason = reason
        transaction.changed = amount
        transaction.insertQueue()
        return transaction
    }


    fun removeAmount(quantity: Double, disconted : Boolean=true) {
        amount -= quantity - if (disconted)(quantity * discont)else 0.0

    }

    fun addAmount(quantity: Double, haveBonus : Boolean=true) {
        amount += quantity + if (haveBonus)(quantity * bonus)else 0.0

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as EconomyUser
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}