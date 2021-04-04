package net.eduard.economy.core

import net.eduard.api.server.currency.CurrencyManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.economy.EduEconomy

class EconomyManager : CurrencyManager() {

    @Transient
    var top = listOf<EconomyUser>()
    @Transient
    val users = mutableMapOf<FakePlayer, EconomyUser>()

    fun getAccount(player: FakePlayer): EconomyUser {
        var account: EconomyUser? = users[player]
        if (account == null) {
            account = EconomyUser()
            account.amount = inicialAmount.toDouble()
            account.player = player
            users[player] = account
            EduEconomy.instance.sqlManager.insertData(account)
        }
        return account
    }

    fun reloadTop() {
        top = users.values.sortedByDescending { it.amount }
    }

    fun addCoins(player: FakePlayer, amount: Double) {
        getAccount(player).addAmount(amount)
    }

    fun removeCoins(player: FakePlayer, amount: Double) {
        getAccount(player).removeAmount(amount)
    }

    fun hasCoins(player: FakePlayer, amount: Double): Boolean {
        return getCoins(player) >= amount
    }


    fun getCoins(player: FakePlayer): Double {
        return getAccount(player).amount
    }

    fun setCoins(player: FakePlayer, amount: Double) {
        getAccount(player).amount = amount
    }

    fun removeAccount(account: EconomyUser) {
        users.remove(account)
    }

    fun clearAccounts() {
        users.clear()
        currency.clear()
    }
}