package net.eduard.economy.command

import net.eduard.api.lib.kotlin.fake
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EconomyTopCommand : CommandManager("top", "rank") {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        val instance = EduEconomyPlugin.instance
        sender.sendMessage(instance.messages.message("top-format-header"))
        for ((user, money) in instance.manager.top) {
            val playerName = user.name
            val pos = user.lastTopPosition
            sender.sendMessage(instance.messages.message("top-format")
                .replace("%player", playerName)
                .replace("%amount", Extra.formatMoney(money))
                .replace("%position", "" + pos)
            )
        }
        if (sender is Player){
            val user = instance.manager.getAccount(sender.fake)
            sender.sendMessage("")
            sender.sendMessage("§6Sua posição é §e${user.lastTopPosition}º §6atualmente.")
        }

        return true
    }

    init {
        description = "Verificar o top de dinheiro"
    }
}