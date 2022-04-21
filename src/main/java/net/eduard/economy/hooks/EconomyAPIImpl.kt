package net.eduard.economy.hooks

import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.server.EconomySystem
import net.eduard.economy.EduEconomyPlugin

class EconomyAPIImpl : EconomySystem {

    val manager get() = EduEconomyPlugin.instance.manager

    override fun setGroupBonus(groupName: String, percent: Double) {
        manager.setGroupBonus(groupName, percent)
    }

    override fun setGroupDiscount(groupName: String, percent: Double) {
        manager.setGroupBonus(groupName , percent)
    }

    override fun getGroupBonus(groupName: String): Double {
        return manager.getGroupBonus(groupName)
    }

    override fun getGroupDiscount(groupName: String): Double {
        return manager.getGroupDiscount(groupName)
    }

    override fun getSellAmountFixed(playerName: String, amount: Double, pricePerAmount: Double): Double {
        val limit = getSellLimit(playerName)
        val gain = pricePerAmount * amount
        if (gain > limit){
            return limit / pricePerAmount
        }
        return amount
    }

    override fun getSellLimit(playerName: String): Double {
        return manager.getAccount(FakePlayer(playerName)).sellLimit
    }

    override fun check(playerName: String, amount: Double): Boolean {
        return manager.hasCoins(FakePlayer(playerName), amount)
    }

    override fun checkBuyLimit(playerName: String, amount: Double): Boolean {
        return manager.getAccount(FakePlayer(playerName)).buyLimit >= amount
    }

    override fun getBonusMultiplier(playerName: String): Double {
        return manager.getAccount(FakePlayer(playerName)).bonus
    }

    override fun getBuyAmountFixed(playerName: String, amount: Double, pricePerAmount: Double): Double {
      val limit = getBuyLimit(playerName)
        val pay = pricePerAmount * amount
        if (pay > limit){
            return limit / pricePerAmount
        }
        return amount
    }

    override fun getBuyLimit(playerName: String): Double {
       return manager.getAccount(FakePlayer(playerName)).buyLimit
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

    override fun modifyBuyLimit(playerName: String, amount: Double) {
        manager.getAccount(FakePlayer(playerName)).buyLimit = amount
    }

    override fun modifySellLimit(playerName: String, amount: Double) {
        manager.getAccount(FakePlayer(playerName)).sellLimit = amount
    }

    override fun pay(payerName: String, receiverName: String, amount: Double) {
        manager.tradeCoins(FakePlayer(payerName), FakePlayer(receiverName), amount)
    }

    override fun take(playerName: String, amount: Double) {
        manager.removeCoins(FakePlayer(playerName), amount)
    }

    override fun takeBuyLimit(playerName: String, amount: Double) {
        manager.getAccount(FakePlayer(playerName)).buyLimit -= amount
    }

    override fun takeDiscounted(playerName: String, amount: Double): Double {
        manager.removeCoins(FakePlayer(playerName), amount, true)
        return  0.0
    }

    override fun takeSellLimit(playerName: String, amount: Double) {
        manager.getAccount(FakePlayer(playerName)).sellLimit -= amount
    }

    override fun checkDiscounted(playerName: String, amount: Double): Boolean {
        return manager.hasCoins(FakePlayer(playerName) , amount, true);
    }

    override fun checkSellLimit(playerName: String, amount: Double): Boolean {
        return manager.getAccount(FakePlayer(playerName)).sellLimit >= amount
    }

    override fun giveBonus(playerName: String, amount: Double): Double {
        manager.addCoins(FakePlayer(playerName), amount , true)
        return 0.0
    }

    override fun giveBuyLimit(playerName: String, amount: Double) {
        manager.getAccount(FakePlayer(playerName)).buyLimit += amount
    }

    override fun giveSellLimit(playerName: String, amount: Double) {
        manager.getAccount(FakePlayer(playerName)).sellLimit += amount
    }
}