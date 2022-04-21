package net.eduard.economy.core

import net.eduard.api.lib.database.SQLManager
import net.eduard.api.lib.database.annotations.*
import net.eduard.api.lib.database.api.DatabaseElement
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.VaultAPI
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.entity.Player

@TableName("economy_users")
class EconomyUser : DatabaseElement {
    override val sqlManager: SQLManager
        get() = EduEconomyPlugin.instance.sqlManager

    @ColumnPrimary
    var id = 0

    @ColumnUnique
    var player = FakePlayer("Eduard")

    val isTycoon get() = lastTopPosition == 1

    @Transient
    var lastTopPosition = 0

    val name get() = player.name
    val inserted get() = id > 0
    var amount = 0.0
        set(value) {
            field = value
            if (inserted) {
                updateQueue()
            } else if (value != 0.0) {
                insertQueue()
            }
        }

    fun save() {
        if (!inserted) {
            insertQueue()
        } else {
            updateQueue()
        }
    }

    var bonus = 0.0
    var discount = 0.0
    @ColumnValue("1000000000")
    var buyLimit = 1_000_000_000.0
    @ColumnValue("10000000")
    var sellLimit = 10_000_000.0

    fun transaction(reason: String, amount: Double): EconomyTransaction {
        val transaction = EconomyTransaction()
        transaction.user = this
        transaction.reason = reason
        transaction.changed = amount
        if (inserted)
            transaction.insertQueue()
        return transaction
    }

    fun removeAmount(quantity: Double, disconted: Boolean = false) {
        amount -= quantity - (if (disconted) (quantity * discount) else 0.0)
    }

    fun addAmount(quantity: Double, haveBonus: Boolean = false) {
        amount += quantity + (if (haveBonus) (quantity * bonus) else 0.0)
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


    fun updateBonusAndDiscount() {
        var bonusValue = 0.0
        var discountValue = 0.0
        val world: String? = null
        for (group in VaultAPI.getPermission().getPlayerGroups(world, player)) {
            bonusValue += EduEconomyPlugin.instance.manager.groupsBonus[group.toLowerCase()] ?: 0.0
            discountValue += EduEconomyPlugin.instance.manager.groupsDiscount[group.toLowerCase()] ?: 0.0
        }
        bonus = bonusValue
        discount = discountValue
        if (bonus != 0.0 || discount != 0.0) {
            save()
        }
    }

    fun updateBonusAndDiscount(player: Player) {
        var bonusValue = 0.0
        var discountValue = 0.0
        for (group in VaultAPI.getPermission().getPlayerGroups(player)) {
            bonusValue += EduEconomyPlugin.instance.manager.groupsBonus[group.toLowerCase()] ?: 0.0
            discountValue += EduEconomyPlugin.instance.manager.groupsDiscount[group.toLowerCase()] ?: 0.0
        }
        bonus = bonusValue
        discount = discountValue
        if (bonus != 0.0 || discount != 0.0) {
            save()
        }
    }
}