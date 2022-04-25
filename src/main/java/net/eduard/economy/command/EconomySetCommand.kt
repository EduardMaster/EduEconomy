package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender
import kotlin.math.abs

class EconomySetCommand : CommandManager("set", "setar", "difinir") {
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2) {
            sendUsage(sender)
            return
        }
        val player = FakePlayer(args[0])
        var valor = Extra.fromMoneyToDouble(args[1])
        valor = abs(valor)
        EduEconomyPlugin.instance.manager.setCoins(player, valor)
        if (args.size >= 3) {
            if (args[2] == "-msg") {
                return
            }
        }
        sender.sendMessage(
            EduEconomyPlugin.instance.message("money-set")
                .replace("%player", player.name)
                .replace("%amount", Extra.formatMoney(valor))
        )
        player
            .sendMessage(
                EduEconomyPlugin.instance.message("money-changed")
                    .replace("%player", sender.name)
            )
    }

    init {
        usage = "/money set <jogador> <quantidade>"
        description = "Definir saldo para o jogador"
    }
}