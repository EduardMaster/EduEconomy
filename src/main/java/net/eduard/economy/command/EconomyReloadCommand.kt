package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.economy.EduEconomy
import org.bukkit.command.CommandSender

class EconomyReloadCommand : CommandManager("reload", "recarregar") {

    override fun command(sender: CommandSender, args: Array<String>) {
        EduEconomy.instance.reload()
        sender.sendMessage("§aRecarregando sistema")
    }

    init {
        description = "Recarregar o sistema de economia"
    }
}