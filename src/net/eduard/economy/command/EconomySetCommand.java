
package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.game.FakePlayer;

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
			Double valor = Extra.fromMoneyToDouble(args[2]);
			valor = Math.abs(valor);
			EduEconomy.getInstance().getManager().setBalance(fakeplayer, valor);
			if (args.length >= 4){
				if (args[3] ==("-msg")){
					return true;
				}
			}
			sender.sendMessage(EduEconomy.getInstance().message("money-set").replace("$player", fakeplayer.getName())
					.replace("$amount", Extra.formatMoney(valor)));
			if (fakeplayer.getPlayer() != null) {
				fakeplayer.getPlayer()
						.sendMessage(EduEconomy.getInstance().message("money-changed").replace("$player", sender.getName()));
			}
		}

		return true;
	}

}
