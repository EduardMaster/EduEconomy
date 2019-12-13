
package net.eduard.economy;

import net.eduard.api.lib.manager.CurrencyManager;
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
import net.eduard.economy.manager.FeatherBoardSupport;
import net.eduard.economy.manager.VaultSupport;
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

		if (Mine.hasPlugin("Vault")) {
			Bukkit.getServicesManager().register(Economy.class, new VaultSupport(), this, ServicePriority.Normal);
			VaultAPI.setupVault();
		}
		if (Mine.hasPlugin("FeatherBoard")) {
			log("Ativando suporte ao 'FeatherBoard' variavel $money");
			new FeatherBoardSupport();
		}
		StorageAPI.register(CurrencyManager.class);
		StorageAPI.register(EconomyManager.class);

		reload();

	}

	public void save() {
		if (manager != null) {
			Bukkit.getServicesManager().unregister(Economy.class, manager);
			storage.set("economy", manager);
			storage.saveConfig();
		}
	}

	public void reload() {
		if (manager != null) {
			Bukkit.getServicesManager().unregister(manager);
		}
		config.reloadConfig();
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
