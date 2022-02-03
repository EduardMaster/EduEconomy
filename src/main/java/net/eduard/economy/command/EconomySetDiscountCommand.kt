package net.eduard.economy.command

import net.eduard.api.lib.kotlin.percent
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomySetDiscountCommand : CommandManager(
    "setdiscount",
    "definirdesconto"
) {

    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2 ){
            sendUsage(sender)
            return
        }
        val grupo = args[0]
        val discountPercent =  Extra.toDouble(args[1])
        EduEconomyPlugin.instance.manager.setGroupDiscount(grupo,discountPercent )
        sender.sendMessage("§aVocê definiu desconto do grupo $grupo para ${discountPercent.percent()}%")
    }


    init {
        usage = "/money setdiscount <group> <percent>"
        description = "Definir Bonus dos Grupos"
    }
}