
package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.player.FakePlayer;

public class EconomyPayCommand extends CommandManager {

    public EconomyPayCommand() {
        super("pay", "pagar");
        setUsage("/money pagar <jogador> <quantidade>");
        setDescription("Pagar o jogador");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (Mine.onlyPlayer(sender)) {
            Player p = (Player) sender;
            if (args.length < 3) {
                sendUsage(sender);
            } else {
                String alvonome = args[1];
                FakePlayer alvoConta = new FakePlayer(alvonome);
                FakePlayer playerConta = new FakePlayer(p);
                Double quantidade = Extra.fromMoneyToDouble(args[2]);
                quantidade = Math.abs(quantidade);


                if (EduEconomy.getInstance().getManager().hasCoins(playerConta, quantidade)) {
                    EduEconomy.getInstance().getManager().addCoins(alvoConta, quantidade);
                    EduEconomy.getInstance().getManager().removeCoins(playerConta, quantidade);
                    sender.sendMessage(EduEconomy.getInstance().message("money-pay").
                            replace("$amount",Extra.formatMoney(quantidade)).
                            replace("$player",alvonome));

                } else {
                    sender.sendMessage(EduEconomy.getInstance().message("money-need"));
                }


            }
        }
        return true;
    }

}
