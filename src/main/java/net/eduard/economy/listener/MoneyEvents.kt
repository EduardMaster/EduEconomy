package net.eduard.economy.listener

import net.eduard.api.lib.manager.EventsManager
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent

class MoneyEvents : EventsManager() {
    @EventHandler
    fun event(e: PlayerMoveEvent?) {
    }

    @EventHandler
    fun event(e: PlayerJoinEvent?) {
    }

    @EventHandler
    fun event(e: PlayerQuitEvent?) {
    }
}