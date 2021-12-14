package net.eduard.economy.command

import net.eduard.api.lib.kotlin.format
import net.eduard.api.lib.kotlin.percent
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.CommandSender

class EconomyInfoCommand : CommandManager("info", "status") {
    override fun command(sender: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            sender.sendMessage("§aBonus por Grupo")
            for ((group, percent) in EduEconomyPlugin.instance.manager.groupsBonus) {
                sender.sendMessage("§2$group -> ${percent.percent()}%")
            }
            sender.sendMessage("§bDescontos por Grupo")
            for ((group, percent) in EduEconomyPlugin.instance.manager.groupsDiscount) {
                sender.sendMessage("§3$group -> ${percent.percent()}%")
            }
        }else{

            val user = EduEconomyPlugin.instance.manager.getAccount(FakePlayer(args[0]))
            sender.sendMessage("§bInformações do ${user.name}")
            sender.sendMessage("§aSaldo: §2${user.amount.format()}")
            sender.sendMessage("§aDesconto: §2${user.discount.percent()}%")
            sender.sendMessage("§aBonus: §2${user.bonus.percent()}%")
        }
    }

    init {
        usage= "/money info [jogador]"
        description = "Ver as configs atuais do Sistema de Economia"
    }
}