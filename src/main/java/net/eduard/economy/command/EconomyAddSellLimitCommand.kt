package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender
import kotlin.math.abs

class EconomyAddSellLimitCommand : CommandManager("addselllimit", "adicionarlimitedevenda") {

    init {
        usage = "/money addselllimit <player> <limit>"
        description = "Adicionar Limite de Venda para o jogador"
    }

    override fun command(sender: CommandSender, args: Array<String>) {

        if (args.size < 2) {
            sendUsage(sender)
            return
        }
        val player = FakePlayer(args[0])
        var quantidade = Extra.fromMoneyToDouble(args[1])
        quantidade = abs(quantidade)
        val conta = EduEconomyPlugin.instance.manager.getAccount(player)
        conta.sellLimit += quantidade

        if (args.size >= 3) {
            if (args[2] == "-msg") {
                return
            }
        }
        sender.sendMessage(
            EduEconomyPlugin.instance.message("sell-limit-add")
                .replace("%player", player.name)
                .replace("%amount", Extra.formatMoney(quantidade))
        )
        player.sendMessage(
            EduEconomyPlugin.instance.message("sell-limit-changed")
                .replace("%player", sender.name)
        )

    }


}