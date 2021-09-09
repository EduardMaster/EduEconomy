package net.eduard.economy.listener

import net.eduard.api.lib.kotlin.fake
import net.eduard.api.lib.manager.EventsManager
import net.eduard.economy.EduEconomy
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

class MoneyListener : EventsManager() {

    @EventHandler
    fun join(e : PlayerJoinEvent){
        EduEconomy.instance.manager.getAccount(e.player.fake)
    }

}