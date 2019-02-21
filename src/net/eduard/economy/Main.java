
package net.eduard.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import net.eduard.api.lib.storage.StorageAPI;
import net.eduard.api.server.EduardPlugin;
import net.eduard.api.server.Systems;
import net.eduard.economy.command.EconomyCommand;
import net.eduard.economy.event.MoneyEvents;
import net.eduard.economy.manager.EconomyManager;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

public class Main extends EduardPlugin {
	private static Main plugin;

	public static Main getInstance() {
		return plugin;
	} 

	private static EconomyManager manager;

	@Override
	public void onEnable() {
		plugin = this;
		new MoneyEvents().register(this);
		new EconomyCommand().register();
		startAutoSave();
		StorageAPI.register(EconomyManager.class);
		reload();
		
	}

	public void save() {
		storage.set("economy", manager);
		storage.saveConfig();
	}

	public void reload() {
		if (manager!=null) {
			Bukkit.getServicesManager().unregister(manager);
		}
		messages.reloadConfig();
		storage.reloadConfig();
		if (storage.contains("economy")) {
			manager = (EconomyManager) storage.get("economy");
		} else {
			manager = new EconomyManager();
			save();
		}
		Systems.setCoinSystem(manager);
		Bukkit.getServicesManager().register(Economy.class, manager, Vault.getPlugin(Vault.class),
				ServicePriority.Normal);
	}

	@Override
	public void onDisable() {
		Bukkit.getServicesManager().unregister(manager);
		save();
	}

	public static EconomyManager getManager() {
		return manager;
	}

	public static void setManager(EconomyManager manager) {
		Main.manager = manager;
	}

}
