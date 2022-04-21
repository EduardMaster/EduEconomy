package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender
import kotlin.math.abs

class EconomyRemoveSellLimitCommand : CommandManager("removebuylimit",  "removerlimitedevenda") {
    init {
        usage = "/money removeselllimit <player> <limit>"
        description = "Remover Limite de Venda do jogador"
    }
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2) {
            sendUsage(sender)
            return
        }
        val fakeplayer = FakePlayer(args[0])
        var quantidade = Extra.fromMoneyToDouble(args[1])
        quantidade = abs(quantidade)
        EduEconomyPlugin.instance.manager.removeCoins(fakeplayer, quantidade)
        if (args.size >= 3) {
            if (args[2] == "-msg") {
                return
            }
        }
        sender.sendMessage(
            EduEconomyPlugin.instance.message("sell-limit-remove")
                .replace("%player", fakeplayer.name)
                .replace("%amount", Extra.formatMoney(quantidade))
        )
        fakeplayer
            .sendMessage(
                EduEconomyPlugin.instance.message("sell-limit-changed")
                    .replace("%player", sender.name)
            )
    }


}