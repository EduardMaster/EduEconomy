
package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.Main;

public class EconomySetCommand extends CommandManager {

	public EconomySetCommand() {
		super("set", "setar", "define", "difinir");
		setUsage("/money set <jogador> <quantidade>");
		setDescription("Definir saldo para o jogador");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 3) {
			sendUsage(sender);
		} else {
			FakePlayer fakeplayer = new FakePlayer(args[1]);
			Main.getInstance().getManager().setBalance(fakeplayer, Extra.toDouble(args[2]));
			sender.sendMessage(Main.getInstance().message("money-set").replace("$player", fakeplayer.getName())
					.replace("$amount", Extra.MONEY.format(Extra.toDouble(args[2]))));
			if (fakeplayer.getPlayer() != null) {
				fakeplayer.getPlayer()
						.sendMessage(Main.getInstance().message("money-changed").replace("$player", sender.getName()));
			}
		}

		return true;
	}

}
