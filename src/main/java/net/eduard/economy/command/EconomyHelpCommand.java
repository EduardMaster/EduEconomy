
package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;

public class EconomyHelpCommand extends CommandManager {

	public EconomyHelpCommand() {
		super("help","ajuda","?");
		setDescription("Ver como funciona os comandos");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		for (CommandManager cmd : getParent().getCommands().values()) {
			sender.sendMessage("§7"+cmd.getUsage()+" §8- §f"+cmd.getDescription());
		}
		return true;
	}

}
