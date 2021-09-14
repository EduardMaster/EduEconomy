package net.eduard.economy.core

import net.eduard.api.lib.kotlin.format
import net.eduard.api.server.currency.CurrencyManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.economy.EduEconomy


class EconomyManager : CurrencyManager() {

    var groupsBonus = mutableMapOf<String , Double>()
    var groupsDiscont = mutableMapOf<String, Double>()
    @Transient
    var top = listOf<EconomyUser>()
    @Transient
    val users = mutableMapOf<FakePlayer, EconomyUser>()

    fun getAccount(player: FakePlayer): EconomyUser {
        var account = users[player]
        if (account == null) {
            account = EconomyUser()
            account.amount = inicialAmount.toDouble()
            account.player = player
            users[player] = account
            EduEconomy.instance.sqlManager.insertDataQueue(account)
        }
        return account
    }

    fun reloadTop() {
        top = users.values.sortedByDescending { it.amount }
    }
    fun tradeCoins(fromPlayer : FakePlayer, toPlayer : FakePlayer, amount : Double){
        val fromUser = getAccount(fromPlayer)
        val toUser = getAccount(toPlayer)

        toUser.addAmount(amount,false)
        fromUser.removeAmount(amount,false)
        fromUser.transaction("Paid ${amount.format()} to ${toPlayer.name}", -amount)
        toUser.transaction("Received ${amount.format()} from ${fromPlayer.name}", +amount)
    }

    fun addCoins(player: FakePlayer, amount: Double) {
        val user = getAccount(player)
        user.addAmount(amount)
        user.transaction("Added ${amount.format()}" , +amount )
    }

    fun removeCoins(player: FakePlayer, amount: Double) {
        val user = getAccount(player)
        user.removeAmount(amount)
        user.transaction("Removed ${amount.format()}" , -amount )
    }

    fun hasCoins(player: FakePlayer, amount: Double, disconted: Boolean=true): Boolean {
        val user = getAccount(player)
        return user.amount >= (amount - if (disconted)(amount*user.discont) else 0.0)
    }


    fun getCoins(player: FakePlayer): Double {
        return getAccount(player).amount
    }

    fun setCoins(player: FakePlayer, amount: Double) {
        val user = getAccount(player)
        val dif =  amount -user.amount
        user.amount = amount
        user.transaction("Setted to ${amount.format()}", dif)
    }

    fun removeAccount(account: EconomyUser) {
        users.remove(account.player)
    }

    fun clearAccounts() {
        users.clear()
        currency.clear()
    }
}