package net.eduard.economy.command

import net.eduard.api.lib.kotlin.format
import net.eduard.api.lib.kotlin.percent
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EconomyInfoCommand : CommandManager("info", "status") {
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            if (sender.hasPermission("$permission.admin")) {
                sender.sendMessage("§6Informações relacionado a Economia")
                sender.sendMessage("§aBonus por Grupo")
                for ((group, percent) in EduEconomyPlugin.instance.manager.groupsBonus) {
                    sender.sendMessage("§b$group -> §3${percent.percent()}%")
                }
                sender.sendMessage("§aDescontos por Grupo")
                for ((group, percent) in EduEconomyPlugin.instance.manager.groupsDiscount) {
                    sender.sendMessage("§b$group -> §3${percent.percent()}%")
                }
                sender.sendMessage("§eLimite de Compra: §a$§7${EduEconomyPlugin.instance.manager.defaultBuyLimit.format()}")
                sender.sendMessage("§eLimite de Venda: §a$§7${EduEconomyPlugin.instance.manager.defaultSellLimit.format()}")
            }else{
                sendUsage(sender)
            }
        } else {

            val user = EduEconomyPlugin.instance.manager.getAccount(FakePlayer(args[0]))
            sender.sendMessage("§bInformações do §f${user.name}")
            sender.sendMessage("§fSaldo: §a${user.amount.format()}")
            sender.sendMessage("§fDesconto: §a${user.discount.percent()}%")
            sender.sendMessage("§fBonus: §a${user.bonus.percent()}%")
            sender.sendMessage("§eLimite de Compra: §a$§7${user.buyLimit.format()}")
            sender.sendMessage("§eLimite de Venda: §a$§7${user.sellLimit.format()}")
        }
    }

    init {
        usage = "/money info [jogador]"
        description = "Ver as configs atuais do Sistema de Economia"
    }
}