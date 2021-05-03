package net.eduard.economy.command;

import net.eduard.economy.menu.MenuMagnata;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.api.lib.manager.CommandManager;

public class MagnataCommand extends CommandManager {

	public MagnataCommand() {
		super("tycoon","magnata");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			MenuMagnata.open(p);
		}
		return true;
	}

}
