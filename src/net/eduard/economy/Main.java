
package net.eduard.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import net.eduard.api.lib.Mine;
import net.eduard.api.lib.modules.VaultAPI;
import net.eduard.api.lib.storage.StorageAPI;
import net.eduard.api.server.EduardPlugin;
import net.eduard.api.server.Systems;
import net.eduard.economy.command.EconomyCommand;
import net.eduard.economy.events.MoneyEvents;
import net.eduard.economy.manager.EconomyManager;
import net.eduard.economy.manager.EconomyVaultSupport;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

public class Main extends EduardPlugin {
	private static Main plugin;

	public static Main getInstance() {
		return plugin;
	} 

	private EconomyManager manager;
	public EconomyManager getManager() {
		return manager;
	}
	
	@Override
	public void onEnable() {
		plugin = this;
		setFree(true);
		new MoneyEvents().register(this);
		new EconomyCommand().register();
		
		StorageAPI.register(EconomyManager.class);
		reload();
		if (Mine.hasPlugin("Vault")) {
			enableVaultSupport();
		}
	
		
		
	}
	public void enableVaultSupport() {
		Bukkit.getServicesManager().register(Economy.class, new EconomyVaultSupport(), this,
				ServicePriority.Normal);
		VaultAPI.setupVault();
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
		
	}

	@Override
	public void onDisable() {
		Bukkit.getServicesManager().unregister(manager);
		save();
	}

}
