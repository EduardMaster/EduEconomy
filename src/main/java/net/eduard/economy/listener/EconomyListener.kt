package net.eduard.economy.listener

import net.eduard.api.lib.kotlin.fake
import net.eduard.api.lib.manager.EventsManager
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

class EconomyListener : EventsManager() {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val user = EduEconomyPlugin.instance.manager.getAccount(e.player.fake)
        user.updateBonusAndDiscount(e.player)
    }

}