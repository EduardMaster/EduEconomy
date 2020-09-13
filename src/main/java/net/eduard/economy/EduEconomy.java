
package net.eduard.economy;

import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.core.PlayerEconomyAccount;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.modules.VaultAPI;
import net.eduard.api.lib.storage.StorageAPI;
import net.eduard.api.server.EduardPlugin;
import net.eduard.economy.command.EconomyCommand;
import net.eduard.economy.listener.MoneyEvents;
import net.eduard.economy.core.EconomyManager;
import net.eduard.economy.hooks.FeatherBoardSupport;
import net.eduard.economy.core.VaultSupport;
import net.milkbowl.vault.economy.Economy;

import java.util.List;

public class EduEconomy extends EduardPlugin {
    private static EduEconomy instance;

    public static EduEconomy getInstance() {
        return instance;
    }

    private EconomyManager manager;

    public EconomyManager getManager() {
        return manager;
    }


    @Override
    public void onEnable() {
        setFree(true);
        super.onEnable();
        instance = this;



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


        StorageAPI.autoRegisterClass(EconomyManager.class);
        reload();



    }

    public void save() {
        if (manager != null) {
            getStorage().set("economy", manager);
            getStorage().saveConfig();
            manager.setSaving(true);
            if (EduEconomy.getInstance().getDbManager().hasConnection()) {
                for (PlayerEconomyAccount account : EduEconomy.getInstance().getManager().getAccounts()) {
                    if (account.needUpdate()) {
                        account.setNeedUpdate(false);
                        EduEconomy.getInstance().getSqlManager().updateData(account);

                    }
                }
            }
            manager.setSaving(false);
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
            manager = getStorage().get("economy", EconomyManager.class) ;
        } else {
            manager = new EconomyManager();
            save();
        }
        if (getDbManager().isEnabled())
        {

            getDbManager().openConnection();
            if (getDbManager().hasConnection()){

                getSqlManager().createTable(PlayerEconomyAccount.class);
                manager.clearAccounts();
                List<PlayerEconomyAccount> accounts = getSqlManager().getAllData(PlayerEconomyAccount.class);
                for (PlayerEconomyAccount account : accounts){
                    manager.getAccountsMap().put(new FakePlayer(account.getPlayerName()),account);
                }

            }
        }


    }

    @Override
    public void onDisable() {
        save();
        super.onDisable();


    }

    public void saveAccount(FakePlayer player) {
        if (getDbManager().hasConnection()) {
            PlayerEconomyAccount account = manager.getAccount(player);
            getSqlManager().updateData(account);
            account.setNeedUpdate(false);
        }
    }
}
