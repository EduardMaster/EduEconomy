package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import org.bukkit.command.CommandSender

class EconomyHelpCommand : CommandManager("help", "ajuda", "?") {
    override fun command(sender: CommandSender, args: Array<String>) {
        for (cmd in parent!!.subCommands.values) {
            sender.sendMessage("§7" + cmd.usage + " §8- §f" + cmd.description)
        }
    }

    init {
        description = "Ver como funciona os comandos"
    }
}