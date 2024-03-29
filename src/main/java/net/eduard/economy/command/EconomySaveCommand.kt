package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomySaveCommand : CommandManager("save", "salvar") {
    override fun command(sender: CommandSender, args: Array<String>) {
        EduEconomyPlugin.instance.save()
        sender.sendMessage("§aSalvando sistema de economia")
    }


    init {
        description = "Salvar o sistema de economia"
    }
}