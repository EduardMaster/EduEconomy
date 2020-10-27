
package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.EduEconomy;

public class EconomyShowCommand extends CommandManager {

	public EconomyShowCommand() {
		super("show", "ver", "checar", "check");
		setDescription("Ver o dinheiro de outro jogador");
		setUsage("/economy ver <jogador>");

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 2) {
			sendUsage(sender);
		} else {
			String jogador = args[1];
			FakePlayer fake = new FakePlayer(jogador);
			String quantidade = Extra.formatMoney(EduEconomy.getInstance()
					.getManager().getCoins(fake));
			sender.sendMessage(EduEconomy.getInstance().message("money-player-check").
					replace("$amount",quantidade).
					replace("$player",jogador));


		}
		return true;
	}

}
