
package net.eduard.economy.command;


import net.eduard.economy.EduEconomy;
import net.eduard.economy.core.PlayerEconomyAccount;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;

public class EconomyTopCommand extends CommandManager {

    public EconomyTopCommand() {
        super("top", "rank");
        setDescription("Verificar o top de dinheiro");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int posicao = 1;
        for (PlayerEconomyAccount conta : EduEconomy.getInstance().getManager().getTop()) {
            String player = conta.getPlayerName();
            double money = conta.getAmount();
            sender.sendMessage(EduEconomy.getInstance().getMessages().message("top-format")
                    .replace("$player", player)
                    .replace("$amount", Extra.formatMoney(money))
                    .replace("$position", "" + posicao));
            posicao++;
        }
        return true;
    }



}
