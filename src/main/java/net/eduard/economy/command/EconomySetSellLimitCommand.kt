package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender
import kotlin.math.abs

class EconomySetSellLimitCommand : CommandManager("setselllimit", "difinirlimitdevenda") {

    init {
        usage = "/money setselllimit <player> <limit>"
        description = "Definir Limite de Venda para o jogador"
    }
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2) {
            sendUsage(sender)
            return
        }
        val fakeplayer = FakePlayer(args[0])
        var valor = Extra.fromMoneyToDouble(args[1])
        valor = abs(valor)
        EduEconomyPlugin.instance.manager.setCoins(fakeplayer, valor)
        if (args.size >= 3) {
            if (args[2] === "-msg") {
                return
            }
        }
        sender.sendMessage(
            EduEconomyPlugin.instance.message("sell-limit-set")
                .replace("%player", fakeplayer.name)
                .replace("%amount", Extra.formatMoney(valor))
        )
        fakeplayer.sendMessage(EduEconomyPlugin.instance
            .message("sell-limit-changed")
            .replace("%player", sender.name))
    }

}