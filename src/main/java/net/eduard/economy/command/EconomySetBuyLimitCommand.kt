package net.eduard.economy.command

import net.eduard.api.lib.kotlin.format
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender
import kotlin.math.abs

class EconomySetBuyLimitCommand : CommandManager("setbuylimit", "difinirlimitdecompra") {

    init {
        usage = "/money setbuylimit <player>|all <limit>"
        description = "Definir Limite de Compra para o jogador"
    }
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.size < 2) {
            sendUsage(sender)
            return
        }
        val playerName = args[0]
        val player = FakePlayer(playerName)
        val quantidade = Extra.fromMoneyToDouble(args[1])
        if (playerName.equals("all",true)||
            playerName.equals("todos",true)){
            for (user in EduEconomyPlugin.instance.manager.users.values){
                user.buyLimit = quantidade
                user.updateQueue()
            }
            sender.sendMessage("§aLimite de Compra de todos jogadores definido para ${quantidade.format()}")
            EduEconomyPlugin.instance.manager.defaultBuyLimit = quantidade
            return
        }
        val conta = EduEconomyPlugin.instance.manager.getAccount(player)
        conta.buyLimit = quantidade
        if (args.size >= 3) {
            if (args[2] === "-msg") {
                return
            }
        }
        sender.sendMessage(
            EduEconomyPlugin.instance.message("buy-limit-set")
                .replace("%player", player.name)
                .replace("%amount", Extra.formatMoney(quantidade))
        )
        player.sendMessage(EduEconomyPlugin.instance
            .message("buy-limit-changed")
            .replace("%player", sender.name))
    }

}