package net.eduard.economy.command

import net.eduard.api.lib.kotlin.format
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender
import kotlin.math.abs

class EconomySetBonusBuyLimitCommand :
    CommandManager("setbonusbuylimit",
        "difinirbonusdelimitdecompra") {

    init {
        usage = "/money setbonusbuylimit <limit>"
        description = "Definir Ganho de Limite de Compra por Tempo de todos jogadores"
    }
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            sendUsage(sender)
            return
        }
        val quantidade = Extra.fromMoneyToDouble(args[1])
        EduEconomyPlugin.instance.manager.bonusBuyLimit = quantidade
        sender.sendMessage(
            EduEconomyPlugin.instance.message("buy-limit-bonus-set")
                .replace("%amount", Extra.formatMoney(quantidade))
        )

    }

}