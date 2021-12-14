package net.eduard.economy.hooks

import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.server.EconomyAPI
import net.eduard.economy.EduEconomyPlugin

class EconomyAPIImpl : EconomyAPI {

    val manager get() = EduEconomyPlugin.instance.manager


    override fun check(player: String, amount: Double): Boolean {
        return manager.hasCoins(FakePlayer(player), amount)
    }


    override fun getBonusMultiplier(player: String): Double {
        return manager.getAccount(FakePlayer(player)).bonus
    }

    override fun getDiscountMultiplier(player: String): Double {
        return manager.getAccount(FakePlayer(player)).discount
    }

    override fun give(player: String, amount: Double) {
        manager.addCoins(FakePlayer(player), amount)
    }


    override fun modify(player: String, amount: Double) {
        manager.setCoins(FakePlayer(player), amount)
    }

    override fun pay(payer: String, receiver: String, amount: Double) {
        manager.tradeCoins(FakePlayer(payer), FakePlayer(receiver), amount)
    }

    override fun take(player: String, amount: Double) {
        manager.removeCoins(FakePlayer(player), amount)
    }

    override fun takeDiscounted(player: String, amount: Double): Double {
        manager.removeCoins(FakePlayer(player), amount, true)
        return  0.0
    }

    override fun checkDiscounted(player: String, amount: Double): Boolean {
        return manager.hasCoins(FakePlayer(player) , amount, true);
    }

    override fun giveBonus(player: String, amount: Double): Double {
        manager.addCoins(FakePlayer(player), amount , true)
        return 0.0
    }
}