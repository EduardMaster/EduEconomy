package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.economy.Main;

public class EconomyCommand extends CommandManager {

	public EconomyCommand() {
		super("money");
		register(new EconomyAddCommand());
		register(new EconomyHelpCommand());
		register(new EconomyRemoveCommand());
		register(new EconomySetCommand());
		register(new EconomyTopCommand());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length == 0) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(Main.getInstance().message("money-check").replace("$amount",
						"" + Extra.MONEY.format(Main.getManager().getBalance(p))));
				return true;
			}
		}

		return super.onCommand(sender, command, label, args);
	}
}