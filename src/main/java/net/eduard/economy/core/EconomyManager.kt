package net.eduard.economy.core

import net.eduard.api.lib.kotlin.format
import net.eduard.api.server.currency.CurrencyManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Mine
import net.eduard.economy.EduEconomyPlugin
import java.util.concurrent.TimeUnit


class EconomyManager : CurrencyManager() {

    var groupsBonus = mutableMapOf<String, Double>()
    var groupsDiscount = mutableMapOf<String, Double>()

    @Transient
    var top = mapOf<EconomyUser, Double>()
    get() {
        if (lastTopUpdate + TimeUnit.MINUTES.toMillis(1) < System.currentTimeMillis()){
            reloadTop()
        }
        return field
    }
    @Transient
    var lastTopUpdate = System.currentTimeMillis()

    @Transient
    var lastTycoon : EconomyUser? = null

    @Transient
    val users = mutableMapOf<FakePlayer, EconomyUser>()

    fun hasAccount(fakePlayer: FakePlayer): Boolean {
        return fakePlayer in users
    }

    fun getAccount(fakePlayer: FakePlayer): EconomyUser {
        var account = users[fakePlayer]
        if (account == null) {
            account = EconomyUser()
            account.player = fakePlayer
            users[fakePlayer] = account
        }
        return account
    }
    fun tycoonChange(tycoonUser : EconomyUser){
        lastTycoon = tycoonUser
        Mine.broadcast(EduEconomyPlugin.instance.message("tycoon-change")
            .replace("%player", tycoonUser.name))
    }

    fun reloadTop() {
        lastTopUpdate = System.currentTimeMillis()
        val topSorted = users.values.sortedByDescending { it.amount }
        var pos = 1;
        for (user in topSorted) {
            if (pos == 1 && user != lastTycoon){
                tycoonChange(user)

            }
            user.lastTopPosition = pos
            pos++
        }
        val limit = if (topSorted.size>10)10 else topSorted.size
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