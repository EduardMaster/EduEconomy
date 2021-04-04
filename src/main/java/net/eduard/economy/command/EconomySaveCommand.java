package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;

public class EconomySaveCommand extends CommandManager {

	public EconomySaveCommand() {
		super("save", "salvar");
		setDescription("Salvar o sistema de economia");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		EduEconomy.getInstance().save();
		sender.sendMessage("§aSalvando sistema de economia");

		return true;
	}

}
