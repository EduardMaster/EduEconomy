package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.Extra
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EconomySellLimitCommand : CommandManager("selllimit","limitedevenda") {
    init{
        usage = "/money selllimit [player]"
        description = "Ver Limite de Venda do jogador"
    }

    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()){
            if (sender is Player) {
                sender.sendMessage(EduEconomyPlugin.instance.message("sell-limit-check")
                    .replace("%amount", "" + Extra.formatMoney(
                    EduEconomyPlugin.instance.manager.getCoins(FakePlayer(sender)))))
            }else{
                sendUsage(sender)
            }
        }else{
            val playerName = args[0]
            val player = FakePlayer(playerName)
            val user = EduEconomyPlugin.instance.manager.getAccount(player)
            sender.sendMessage(EduEconomyPlugin.instance.message("sell-limit-check")
                .replace("%amount", "" + Extra.formatMoney(
                    user.buyLimit)))
        }
    }


}