
package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.player.FakePlayer;

public class EconomyRemoveCommand extends CommandManager {

	public EconomyRemoveCommand() {
		super("remove","take","tirar","remover");
		setUsage("/money remover <jogador> <quantidade>");
		setDescription("Remover saldo do jogador");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length < 3) {
			sendUsage(sender);
		} else {
			FakePlayer fakeplayer = new FakePlayer(args[1]);
			double valor = Extra.fromMoneyToDouble(args[2]);
			EduEconomy.getInstance().getManager().removeBalance(fakeplayer, valor);
			sender.sendMessage(EduEconomy.getInstance().message("money-remove").replace("$player", fakeplayer.getName())
					.replace("$amount", Extra.formatMoney(Extra.toDouble(args[2]))));
			if (fakeplayer.getPlayer() != null) {
				fakeplayer.getPlayer()
						.sendMessage(EduEconomy.getInstance().message("money-changed").replace("$player", sender.getName()));
			}
		}

		return true;
	}

}
