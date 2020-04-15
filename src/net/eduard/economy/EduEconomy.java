
package net.eduard.economy;

import net.eduard.api.lib.manager.CurrencyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.modules.VaultAPI;
import net.eduard.api.lib.storage.StorageAPI;
import net.eduard.api.server.EduardPlugin;
import net.eduard.api.server.Systems;
import net.eduard.economy.command.EconomyCommand;
import net.eduard.economy.listener.MoneyEvents;
import net.eduard.economy.core.EconomyManager;
import net.eduard.economy.addon.FeatherBoardSupport;
import net.eduard.economy.core.VaultSupport;
import net.milkbowl.vault.economy.Economy;

public class EduEconomy extends EduardPlugin {
    private static EduEconomy plugin;

    public static EduEconomy getInstance() {
        return plugin;
    }

    private EconomyManager manager;

    public EconomyManager getManager() {
        return manager;
    }

    @Override
    public void onEnable() {
        setFree(true);
        super.onEnable();
        plugin = this;

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

        StorageAPI.register(EconomyManager.class);
        StorageAPI.register(CurrencyManager.class);
        reload();

    }

    public void save() {
        if (manager != null) {

            getStorage().set("economy", manager);
            getStorage().saveConfig();
        }
    }

    public void reload() {
        if (manager != null) {
            unregisterServices();
        }
        getConfigs().reloadConfig();
        getMessages().reloadConfig();
        getStorage().reloadConfig();
        if (getStorage().contains("economy")) {
            Object dado = getStorage().get("economy");
           // System.out.println("Tipo do dado: "+dado);
            manager = (EconomyManager) dado ;
        } else {
            manager = new EconomyManager();
            save();
        }
        Systems.setCoinSystem(manager);

    }

    @Override
    public void onDisable() {
        save();
        super.onDisable();


    }

}
