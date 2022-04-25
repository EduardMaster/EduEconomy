package net.eduard.economy.command

import net.eduard.api.lib.kotlin.formatDuration
import net.eduard.api.lib.kotlin.parseDuration
import net.eduard.api.lib.manager.CommandManager
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomySetBonusSellLimitDelayCommand : CommandManager(
    "setbonusselllimitdelay",
    "difinirbonusdelimitdevendatempo"
) {
    init {
        usage = "/money setbonusbuylimitdelay <time|s|m|d>"
        description = "Definir Tempo entre os Aumentos de Limite de Venda de todos jogadores"
    }

    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            sendUsage(sender)
            return
        }
        val timeDelayText = args[1]
        val timeDelay = timeDelayText.parseDuration()
        EduEconomyPlugin.instance.manager.bonusSellLimitDelay = timeDelay
        sender.sendMessage("§aTempo entre os Aumentos de Limite de Venda definido para ${timeDelay.formatDuration()}")
    }


}