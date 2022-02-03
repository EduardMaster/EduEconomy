package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.entity.Player
import kotlin.math.abs

class EconomyPayCommand : CommandManager("pay", "pagar") {
    override fun playerCommand(player: Player, args: Array<String>) {
        if (args.size < 2) {
            sendUsage(player)
            return
        }
        val name = args[0]
        val target = FakePlayer(name)
        val playerConta = FakePlayer(player)
        var quantidade = Extra.fromMoneyToDouble(args[1])
        quantidade = abs(quantidade)
        val instance = EduEconomyPlugin.instance
        if (target == playerConta) {
            player.sendMessage(instance.message("cant-pay-self"))
            return
        }
        if (quantidade < 1) {
            player.sendMessage(
                instance.message("money-pay-invalid")
                    .replace("%amount", Extra.formatMoney(quantidade))
            )
            return
        }
        if (instance.manager.hasCoins(playerConta, quantidade, false)) {
            instance.manager.tradeCoins(playerConta, target, quantidade)
            player.sendMessage(
                instance.message("money-pay")
                    .replace("%amount", Extra.formatMoney(quantidade))
                    .replace("%player", name)
            )
            target.sendMessage(
                instance.message("money-payment")
                    .replace("%amount", Extra.formatMoney(quantidade))
                    .replace("%player", player.name)
            )
        } else {
            player.sendMessage(instance.message("money-need"))
        }
    }

    init {
        usage = "/money pagar <jogador> <quantidade>"
        description = "Pagar o jogador"
    }
}