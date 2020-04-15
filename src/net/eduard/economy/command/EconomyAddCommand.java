
package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.player.FakePlayer;

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
		
			sender.sendMessage(EduEconomy.getInstance().message("money-add").replace("$player", fakeplayer.getName())
					.replace("$amount", Extra.formatMoney(amount)));
			EduEconomy.getInstance().getManager().addCoins(fakeplayer, amount);
			if (fakeplayer.getPlayer() != null) {
				fakeplayer.getPlayer().sendMessage(
						EduEconomy.getInstance().message("money-changed").replace("$player", sender.getName()));
			}
		}

		return true;
	}

}
