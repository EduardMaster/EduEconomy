package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender
import kotlin.math.abs

class EconomyAddCommand : CommandManager("add", "adicionar") {
    override fun command(sender: CommandSender, args: Array<String>) {

        if (args.size < 2) {
            sendUsage(sender)
            return
        }
        val player = FakePlayer(args[0])
        var quantidade = Extra.fromMoneyToDouble(args[1])
        quantidade = abs(quantidade)
        EduEconomyPlugin.instance.manager.addCoins(player, quantidade)
        if (args.size >= 3) {
            if (args[2] == "-msg") {
                return
            }
        }
        sender.sendMessage(
            EduEconomyPlugin.instance.message("money-add")
                .replace("%player", player.name)
                .replace("%amount", Extra.formatMoney(quantidade))
        )
        player.sendMessage(
            EduEconomyPlugin.instance.message("money-changed")
                .replace("%player", sender.name)
        )

    }


    init {
        usage = "/money add <jogador> <quantidade>"
        description = "Adicionar saldo para o jogador"
    }
}