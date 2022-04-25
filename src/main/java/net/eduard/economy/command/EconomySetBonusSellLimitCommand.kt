package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomySetBonusSellLimitCommand : CommandManager("setbonusselllimit", "difinirbonusdelimitdevenda") {

    init {
        usage = "/money setbonusselllimit <limit>"
        description = "Definir Ganho de Limite de Venda por Tempo de todos jogadores"
    }
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            sendUsage(sender)
            return
        }
        val quantidade = Extra.fromMoneyToDouble(args[0])
        EduEconomyPlugin.instance.manager.bonusSellLimit  = quantidade
        sender.sendMessage(
            EduEconomyPlugin.instance.message("sell-limit-bonus-set")
                .replace("%amount", Extra.formatMoney(quantidade))
        )


    }

}