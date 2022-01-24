package net.eduard.economy.hooks

import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.server.EconomySystem
import net.eduard.economy.EduEconomyPlugin

class EconomyAPIImpl : EconomySystem {

    val manager get() = EduEconomyPlugin.instance.manager

    override fun check(playerName: String, amount: Double): Boolean {
        return manager.hasCoins(FakePlayer(playerName), amount)
    }

    override fun getBonusMultiplier(playerName: String): Double {
        return manager.getAccount(FakePlayer(playerName)).bonus
    }

    override fun getDiscountMultiplier(playerName: String): Double {
        return manager.getAccount(FakePlayer(playerName)).discount
    }

    override fun give(playerName: String, amount: Double) {
        manager.addCoins(FakePlayer(playerName), amount)
    }


    override fun modify(playerName: String, amount: Double) {
        manager.setCoins(FakePlayer(playerName), amount)
    }

    override fun pay(payerName: String, receiverName: String, amount: Double) {
        manager.tradeCoins(FakePlayer(payerName), FakePlayer(receiverName), amount)
    }

    override fun take(playerName: String, amount: Double) {
        manager.removeCoins(FakePlayer(playerName), amount)
    }

    override fun takeDiscounted(playerName: String, amount: Double): Double {
        manager.removeCoins(FakePlayer(playerName), amount, true)
        return  0.0
    }

    override fun checkDiscounted(playerName: String, amount: Double): Boolean {
        return manager.hasCoins(FakePlayer(playerName) , amount, true);
    }

    override fun giveBonus(playerName: String, amount: Double): Double {
        manager.addCoins(FakePlayer(playerName), amount , true)
        return 0.0
    }
}