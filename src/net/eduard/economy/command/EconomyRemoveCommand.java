
package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.Main;

public class EconomyRemoveCommand extends CommandManager {

	public EconomyRemoveCommand() {
		super("remove","take","tirar","remover");
		setUsage("/money remove <player> <amount>");
		setDescription("Remover saldo do jogador");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length < 3) {
			sendUsage(sender);
		} else {
			FakePlayer fakeplayer = new FakePlayer(args[1]);
			Main.getInstance().getManager().removeBalance(fakeplayer, Mine.toDouble(args[2]));
			sender.sendMessage(Main.getInstance().message("money-remove").replace("$player", fakeplayer.getName())
					.replace("$amount", Extra.MONEY.format(Mine.toDouble(args[2]))));
			if (fakeplayer.getPlayer() != null) {
				fakeplayer.getPlayer()
						.sendMessage(Main.getInstance().message("money-changed").replace("$player", sender.getName()));
			}
		}

		return true;
	}

}
