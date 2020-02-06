
package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.Main;

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
				Double quantidade = Extra.toDouble(args[2]);
				if (quantidade <= 0 || Double.isNaN(quantidade) || Double.isInfinite(quantidade)
						) {
					sender.sendMessage("§cNão pode ser numero negativo.");
				} else {
					if (Main.getInstance().getManager().hasCoins(playerConta, quantidade)) {
						Main.getInstance().getManager().addCoins(alvoConta, quantidade);
						Main.getInstance().getManager().removeCoins(playerConta, quantidade);
						sender.sendMessage("§aVocê pagou " + Extra.MONEY.format(quantidade) + " para " + alvonome);
					} else {
						sender.sendMessage("§cDinheiro insuficiente para poder pagar outro jogador.");
					}
				}

			}
		}
		return true;
	}

}
