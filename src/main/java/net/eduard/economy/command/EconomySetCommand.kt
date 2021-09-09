package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomy
import org.bukkit.command.CommandSender
import kotlin.math.abs

class EconomySetCommand : CommandManager("set", "setar", "define", "difinir") {
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2) {
            sendUsage(sender)
            return
        }
        val fakeplayer = FakePlayer(args[0])
        var valor = Extra.fromMoneyToDouble(args[1])
        valor = abs(valor)
        EduEconomy.instance.manager.setCoins(fakeplayer, valor)
        if (args.size >= 3) {
            if (args[2] === "-msg") {
                return
            }
        }
        sender.sendMessage(
            EduEconomy.instance.message("money-set")
                .replace("%player", fakeplayer.name)
                .replace("%amount", Extra.formatMoney(valor))
        )
        fakeplayer
            .sendMessage(
                EduEconomy.instance.message("money-changed")
                    .replace("%player", sender.name)
            )
    }

    init {
        usage = "/money set <jogador> <quantidade>"
        description = "Definir saldo para o jogador"
    }
}