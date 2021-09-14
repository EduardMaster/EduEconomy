package net.eduard.economy.listener

import net.eduard.api.lib.kotlin.fake
import net.eduard.api.lib.manager.EventsManager
import net.eduard.api.lib.modules.VaultAPI
import net.eduard.economy.EduEconomy
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

class MoneyListener : EventsManager() {

    @EventHandler
    fun join(e : PlayerJoinEvent){
       val user =  EduEconomy.instance.manager.getAccount(e.player.fake)
        var bonus = 0.0
        var discont = 0.0
        for (group in VaultAPI.getPermission().getPlayerGroups(e.player)){
            bonus+= EduEconomy.instance.manager.groupsBonus[group.toLowerCase()]?:0.0
            discont+= EduEconomy.instance.manager.groupsDiscont[group.toLowerCase()]?:0.0
        }
        user.bonus=bonus
        user.discont=discont
    }

}