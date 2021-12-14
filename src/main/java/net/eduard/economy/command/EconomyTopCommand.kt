package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class EconomyTopCommand : CommandManager("top", "rank") {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        var posicao = 1
        val instance = EduEconomyPlugin.instance
        for (conta in instance.manager.top) {
            val player = conta.name
            val money = conta.amount
            sender.sendMessage(
                instance.messages.message("top-format")
                    .replace("%player", player)
                    .replace("%amount", Extra.formatMoney(money))
                    .replace("%position", "" + posicao)
            )
            posicao++
        }
        return true
    }

    init {
        description = "Verificar o top de dinheiro"
    }
}