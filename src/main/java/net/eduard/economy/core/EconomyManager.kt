package net.eduard.economy.core

import net.eduard.api.lib.kotlin.format
import net.eduard.api.server.currency.CurrencyManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Mine
import net.eduard.economy.EduEconomyPlugin
import java.util.concurrent.TimeUnit
import kotlin.math.acos


class EconomyManager : CurrencyManager() {

    var defaultBuyLimit = 100_000_000.0
    var defaultSellLimit = 1_000_000.0
    var bonusBuyLimit = 1_000_000.0
    var bonusBuyLimitDelay = TimeUnit.HOURS.toMillis(1)
    var bonusSellLimit = 1_000_000.0
    var bonusSellLimitDelay = TimeUnit.HOURS.toMillis(1)
    var bonusSellLimitLastTime = System.currentTimeMillis()
    var bonusBuyLimitLastTime = System.currentTimeMillis()
    var groupsBonus = mutableMapOf<String, Double>()
    var groupsDiscount = mutableMapOf<String, Double>()

    @Transient
    var top = mapOf<EconomyUser, Double>()

    @Transient
    var lastTopUpdate = System.currentTimeMillis()

    @Transient
    var lastTycoon: EconomyUser? = null

    @Transient
    val users = mutableMapOf<FakePlayer, EconomyUser>()

    fun hasAccount(fakePlayer: FakePlayer): Boolean {
        return fakePlayer in users
    }

    fun getGroupBonus(groupName: String): Double {
        return groupsBonus[groupName.toLowerCase()] ?: 0.0
    }

    fun getGroupDiscount(groupName: String): Double {
        return groupsDiscount[groupName.toLowerCase()] ?: 0.0
    }

    fun setGroupDiscount(groupName: String, discountPercent: Double) {
        groupsDiscount[groupName.toLowerCase()] = discountPercent
        updatePlayersBonusAndDiscount()
    }

    fun setGroupBonus(groupName: String, bonusPercent: Double) {
        groupsBonus[groupName.toLowerCase()] = bonusPercent
        updatePlayersBonusAndDiscount()
    }

    fun updatePlayersBonusAndDiscount() {
        EduEconomyPlugin.instance.asyncTask {
            for (user in users.values) {
                user.updateBonusAndDiscount()
            }
        }
    }

    fun wipe(): Int {
        val amount = users.size
        EduEconomyPlugin.instance.sqlManager.clearTable<EconomyTransaction>()
        EduEconomyPlugin.instance.sqlManager.clearTable<EconomyUser>()
        users.clear()
        return amount
    }

    fun reset(): Int {
        for (user in users.values) {
            user.buyLimit = defaultBuyLimit
            user.sellLimit = defaultSellLimit
            user.amount = 0.0
        }
        return users.size
    }

    fun getAccount(player: FakePlayer): EconomyUser {
        var account = users[player]
        if (account == null) {
            account = EconomyUser()
            account.player = player
            account.buyLimit = defaultBuyLimit
            account.sellLimit = defaultSellLimit
            users[player] = account
        }
        return account
    }

    fun tycoonChange(tycoonUser: EconomyUser) {
        lastTycoon = tycoonUser
        Mine.broadcast(EduEconomyPlugin.instance.message("tycoon-change")
                .replace("%player", tycoonUser.name))
    }

    fun updateTop() {
        lastTopUpdate = System.currentTimeMillis()
        val topSorted = users.values.sortedByDescending { it.amount }
        var pos = 1;
        for (user in topSorted) {
            if (pos == 1 && user != lastTycoon) {
                tycoonChange(user)

            }
            user.lastTopPosition = pos
            pos++
        }
        val limit = if (topSorted.size > 10) 10 else topSorted.size
        top = topSorted.slice(0 until limit).associateWith { it.amount }
    }

    fun tradeCoins(fromPlayer: FakePlayer, toPlayer: FakePlayer, amount: Double) {
        val fromUser = getAccount(fromPlayer)
        val toUser = getAccount(toPlayer)
        toUser.addAmount(amount, false)
        fromUser.removeAmount(amount, false)
        fromUser.transaction("Paid ${amount.format()} to ${toPlayer.name}", -amount)
        toUser.transaction("Received ${amount.format()} from ${fromPlayer.name}", +amount)
    }

    fun addCoins(player: FakePlayer, amount: Double, withBonus: Boolean = false) {
        val user = getAccount(player)
        user.addAmount(amount, withBonus)
        user.transaction("Added ${amount.format()}", +amount)
    }

    fun removeCoins(player: FakePlayer, amount: Double, discounted: Boolean = false) {
        val user = getAccount(player)
        user.removeAmount(amount, discounted)
        user.transaction("Removed ${amount.format()}", -amount)
    }

    fun hasCoins(player: FakePlayer, amount: Double, discounted: Boolean = false): Boolean {
        val user = getAccount(player)
        if (discounted) {
            return user.amount >= (amount - amount * user.discount)
        }
        return user.amount >= amount
    }


    fun getCoins(player: FakePlayer): Double {
        return getAccount(player).amount
    }

    fun setCoins(player: FakePlayer, amount: Double) {
        val user = getAccount(player)
        val changed = amount - user.amount
        user.amount = amount
        user.transaction("Setted to ${amount.format()}", changed)
    }

    fun removeAccount(account: EconomyUser) {
        users.remove(account.player)
    }

    fun clearAccounts() {
        users.clear()
        currency.clear()
    }

}