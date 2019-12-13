
package net.eduard.economy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.economy.Main;

public class EconomySaveCommand extends CommandManager {

	public EconomySaveCommand() {
		super("save", "salvar");
		setDescription("Salvar o sistema de economia");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Main.getInstance().save();
		sender.sendMessage("§aSalvando sistema de economia");

		return true;
	}

}
