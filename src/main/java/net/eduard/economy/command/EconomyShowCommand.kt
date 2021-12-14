package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomyShowCommand : CommandManager("show", "ver", "checar", "check") {
    override fun command(sender: CommandSender, args: Array<String>) {
        val instance = EduEconomyPlugin.instance
        if (args.isEmpty()) {
            sendUsage(sender)
            return
        }
        val playerName = args[0]
        val fakePlayer = FakePlayer(playerName)
        val quantidade = Extra.formatMoney(
            instance.manager.getCoins(fakePlayer)
        )
        sender.sendMessage(instance
                .message("money-player-check")
            .replace("%amount", quantidade)
            .replace("%player", playerName)
        )

    }


    init {
        description = "Ver o dinheiro de outro jogador"
        usage = "/money ver <jogador>"
    }
}