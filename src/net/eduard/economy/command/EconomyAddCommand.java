
package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.Main;

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
			double amount = Extra.toDouble(args[2]);
		
			sender.sendMessage(Main.getInstance().message("money-add").replace("$player", fakeplayer.getName())
					.replace("$amount", Extra.MONEY.format(amount)));
			Main.getInstance().getManager().addCoins(fakeplayer, amount);
			if (fakeplayer.getPlayer() != null) {
				fakeplayer.getPlayer().sendMessage(
						Main.getInstance().message("money-changed").replace("$player", sender.getName()));
			}
		}

		return true;
	}

}
