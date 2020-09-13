
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
		if (args.length < 3) {
			sendUsage(sender);
		} else {
			FakePlayer fakeplayer = new FakePlayer(args[1]);
			double amount = Extra.fromMoneyToDouble(args[2]);
			amount = Math.abs(amount);


			EduEconomy.getInstance().getManager().addCoins(fakeplayer, amount);
			if (args.length >= 4){
				if (args[3] ==("-msg")){
					return true;
				}
			}
			sender.sendMessage(EduEconomy.getInstance().message("money-add").replace("$player", fakeplayer.getName())
					.replace("$amount", Extra.formatMoney(amount)));
			if (fakeplayer.getPlayer() != null) {
				fakeplayer.getPlayer().sendMessage(
						EduEconomy.getInstance().message("money-changed").replace("$player", sender.getName()));
			}
		}

		return true;
	}

}
