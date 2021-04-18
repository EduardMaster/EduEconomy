
package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;



public class EconomyAddCommand extends CommandManager {

    public EconomyAddCommand() {
        super("add", "adicionar");
        setUsage("/money add <jogador> <quantidade>");
        setDescription("Adicionar saldo para o jogador");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sendUsage(sender);
        } else {
            FakePlayer fakeplayer = new FakePlayer(args[0]);
            double amount = Extra.fromMoneyToDouble(args[1]);
            amount = Math.abs(amount);


            EduEconomy.getInstance().getManager().addCoins(fakeplayer, amount);
            if (args.length >= 3) {
                if (args[2].equals("-msg")) {
                    return true;
                }
            }
            sender.sendMessage(EduEconomy.getInstance().message("money-add")
                    .replace("%player", fakeplayer.getName())
                    .replace("%amount", Extra.formatMoney(amount)));

            fakeplayer.sendMessage(
                    EduEconomy.getInstance().message("money-changed")
                            .replace("%player", sender.getName()));

        }

        return true;
    }

}
