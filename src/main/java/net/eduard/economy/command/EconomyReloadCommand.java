package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.economy.EduEconomy;

public class EconomyReloadCommand extends CommandManager {

	public EconomyReloadCommand() {
		super("reload", "recarregar");
		setDescription("Recarregar o sistema de economia");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		EduEconomy.getInstance().reload();
		sender.sendMessage("§aRecarregando sistema");

		return true;
	}

}
