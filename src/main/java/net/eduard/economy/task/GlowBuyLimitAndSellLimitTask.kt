package net.eduard.economy.task

import net.eduard.api.lib.kotlin.fake
import net.eduard.api.lib.manager.TimeManager
import net.eduard.api.lib.modules.Mine
import net.eduard.economy.EduEconomyPlugin

class GlowBuyLimitAndSellLimitTask : TimeManager(60) {

    override fun run() {
        val manager = EduEconomyPlugin.instance.manager
        val now = System.currentTimeMillis()
        if (now > (manager.bonusBuyLimitLastTime + manager.bonusBuyLimitDelay)) {
            growBuyLimit()
        }
        if (now > (manager.bonusSellLimitLastTime + manager.bonusSellLimitDelay)) {
            growSellLimit()
        }
    }

    fun growBuyLimit(){
        val manager = EduEconomyPlugin.instance.manager
        for (player in Mine.getPlayers()) {
            val user = manager.getAccount(player.fake)
            if (user.buyLimit< manager.defaultBuyLimit)
                user.buyLimit = manager.defaultBuyLimit
            user.buyLimit+= manager.bonusBuyLimit
        }
        manager.bonusBuyLimitLastTime = System.currentTimeMillis()
        manager.defaultBuyLimit+= manager.bonusBuyLimit
    }

    fun growSellLimit(){
        val manager = EduEconomyPlugin.instance.manager
        for (player in Mine.getPlayers()) {
            val user = manager.getAccount(player.fake)
            if (user.sellLimit< manager.defaultSellLimit)
                user.sellLimit = manager.defaultSellLimit
            user.sellLimit+=manager.bonusSellLimit
        }
        manager.bonusSellLimitLastTime = System.currentTimeMillis()
        manager.defaultSellLimit += manager.bonusSellLimit
    }
}