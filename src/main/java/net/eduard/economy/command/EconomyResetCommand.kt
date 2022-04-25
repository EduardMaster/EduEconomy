package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomyResetCommand : CommandManager("reset", "resetar") {

    override fun command(sender: CommandSender, args: Array<String>) {
        val manager = EduEconomyPlugin.instance.manager
        val amount = manager.reset()
        sender.sendMessage(EduEconomyPlugin.instance.message("money-reset-all")
            .replace("%amount", ""+amount))
    }
}