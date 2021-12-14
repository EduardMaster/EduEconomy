package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomyReloadCommand : CommandManager("reload", "recarregar") {

    override fun command(sender: CommandSender, args: Array<String>) {
        EduEconomyPlugin.instance.reload()
        sender.sendMessage(EduEconomyPlugin.instance.message("system-reload"))
    }

    init {
        description = "Recarregar o sistema de economia"
    }
}