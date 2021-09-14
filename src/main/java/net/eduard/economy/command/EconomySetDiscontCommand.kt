package net.eduard.economy.command

import net.eduard.api.lib.kotlin.percent
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomy
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import kotlin.math.abs

class EconomySetDiscontCommand : CommandManager(
    "setdiscont",
    "definirdesconto"
) {

    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2 ){
            sendUsage(sender)
            return
        }
        val grupo = args[0]
        val bonus =  Extra.toDouble(args[1])
        EduEconomy.instance.manager.groupsDiscont[grupo.toLowerCase()] = bonus
        sender.sendMessage("§aVocê definiu desconto do grupo $grupo para ${bonus.percent()}%")
    }


    init {
        usage = "/money setbonus <group> <bonus>"
        description = "Definir Bonus dos Grupos"
    }
}