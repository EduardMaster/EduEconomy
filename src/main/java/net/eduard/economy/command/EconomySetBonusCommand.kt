package net.eduard.economy.command

import net.eduard.api.lib.kotlin.percent
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomySetBonusCommand : CommandManager(
    "setbonus",
    "definirbonus"
) {

    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2 ){
            sendUsage(sender)
            return
        }
        val grupo = args[0]
        val bonus =  Extra.toDouble(args[1])
        EduEconomyPlugin.instance.manager.setGroupBonus(grupo , bonus)
        sender.sendMessage("§aVocê definiu bonus do grupo $grupo para ${bonus.percent()}%")
    }


    init {
        usage = "/money setbonus <group> <percent>"
        description = "Definir Bonus dos Grupos"
    }
}