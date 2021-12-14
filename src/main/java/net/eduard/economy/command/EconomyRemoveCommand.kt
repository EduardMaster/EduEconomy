package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomyRemoveCommand : CommandManager("remove", "take", "tirar", "remover") {
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2) {
            sendUsage(sender)
            return
        }
        val fakeplayer = FakePlayer(args[0])
        var valor = Extra.fromMoneyToDouble(args[1])
        valor = Math.abs(valor)
        EduEconomyPlugin.instance.manager.removeCoins(fakeplayer, valor)
        if (args.size >= 3) {
            if (args[2] == "-msg") {
                return
            }
        }
        sender.sendMessage(
            EduEconomyPlugin.instance.message("money-remove")
                .replace("%player", fakeplayer.name)
                .replace("%amount", Extra.formatMoney(valor))
        )
        fakeplayer
            .sendMessage(
                EduEconomyPlugin.instance.message("money-changed")
                    .replace("%player", sender.name)
            )
    }

    init {
        usage = "/money remover <jogador> <quantidade>"
        description = "Remover saldo do jogador"
    }
}