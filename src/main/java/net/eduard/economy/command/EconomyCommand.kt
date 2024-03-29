package net.eduard.economy.command

import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.Extra
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EconomyCommand : CommandManager("money","coins","coin","dinheiro") {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            if (sender is Player) {
                val player = sender

                player.sendMessage(
                    EduEconomyPlugin.instance.message("money-check")
                        .replace(
                        "%amount",
                        "" + Extra.formatMoney( EduEconomyPlugin.instance.
                        manager.getCoins(FakePlayer(player)))
                    )
                )
                return true
            }
        }
        return super.onCommand(sender, command, label, args)
    }

    init {
        register(EconomyAddCommand())
        register(EconomyInfoCommand())
        register(EconomyRemoveCommand())
        register(EconomySetCommand())
        register(EconomyReloadCommand())
        register(EconomyPayCommand())
        register(EconomyTopCommand())
        register(EconomyShowCommand())
        register(EconomySetBonusCommand())
        register(EconomySetDiscountCommand())
        register(EconomyUpdateDiscountBonusCommand())
        register(EconomyHelpCommand())

        register(EconomyBuyLimitCommand())
        register(EconomyAddBuyLimitCommand())
        register(EconomyRemoveBuyLimitCommand())
        register(EconomySetBuyLimitCommand())

        register(EconomySellLimitCommand())
        register(EconomyAddSellLimitCommand())
        register(EconomyRemoveSellLimitCommand())
        register(EconomySetSellLimitCommand())

        register(EconomySetBonusBuyLimitCommand())
        register(EconomySetBonusSellLimitCommand())
        register(EconomySetBonusBuyLimitDelayCommand())
        register(EconomySetBonusSellLimitDelayCommand())
        register(EconomyResetCommand())

    }
}