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
            user.buyLimit+=manager.bonusBuyLimit
        }
        manager.bonusBuyLimitLastTime = System.currentTimeMillis()
    }

    fun growSellLimit(){
        val manager = EduEconomyPlugin.instance.manager
        for (player in Mine.getPlayers()) {
            val user = manager.getAccount(player.fake)
            user.sellLimit+=manager.bonusSellLimit
        }
        manager.bonusSellLimitLastTime = System.currentTimeMillis()
    }
}