
package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.player.FakePlayer;
import net.eduard.economy.EduEconomy;

public class EconomyShowCommand extends CommandManager {

	public EconomyShowCommand() {
		super("show", "ver", "checar", "check");
		setDescription("Ver o dinheiro de outro jogador");
		setUsage("/economy ver <jogador>");

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			sendUsage(sender);
		} else {
			String jogador = args[0];
			FakePlayer fake = new FakePlayer(jogador);

			sender.sendMessage("§aO dinheiro do jogador " + jogador + " é "
					+ Extra.formatMoney(EduEconomy.getInstance().getManager().getCoins(fake)));

		}
		return true;
	}

}
