package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomyUpdateDiscountBonusCommand : CommandManager("update") {
    override fun command(sender: CommandSender, args: Array<String>) {
        val instance = EduEconomyPlugin.instance
        if (args.isEmpty()) {
            sendUsage(sender)
            return
        }

        val playerName = args[0]
        val fakePlayer = FakePlayer(playerName)
        val user = instance.manager.getAccount(fakePlayer)
        instance.asyncTask{
            user.updateBonusAndDiscount()
        }
        sender.sendMessage(instance.message("money-discount-bonus-update")
            .replace("%player", playerName)
        )
    }


    init {
        description = "Atualiza a conta do Jogador Desconto e Bonus"
        usage = "/money update <jogador>"
    }
}