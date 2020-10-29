
package net.eduard.economy;

import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.core.PlayerEconomyAccount;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.modules.VaultAPI;
import net.eduard.api.server.EduardPlugin;
import net.eduard.economy.command.EconomyCommand;
import net.eduard.economy.listener.MoneyEvents;
import net.eduard.economy.core.EconomyManager;
import net.eduard.economy.hooks.FeatherBoardSupport;
import net.eduard.economy.core.VaultSupport;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.scheduler.BukkitRunnable;

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


        reload();


    }

    public void save() {
        if (getStorageManager().getType().isSQL()){
           manager.getCurrency().clear();
        }
        getStorage().set("economy", manager);
        getStorage().saveConfig();
    }

    public void reload() {

        getConfigs().reloadConfig();
        getMessages().reloadConfig();
        getStorage().reloadConfig();
        if (getStorage().contains("economy")) {
            manager = getStorage().get("economy", EconomyManager.class);
        } else {
            manager = new EconomyManager();
            save();
        }
        Mine.console("§cAtivado "+getDbManager().isEnabled());
        Mine.console("§cConexao "+getDbManager().getConnection());
        getSqlManager().createTable(PlayerEconomyAccount.class);
        List<PlayerEconomyAccount> accounts = getSqlManager().getAllData(PlayerEconomyAccount.class);
        for (PlayerEconomyAccount account : accounts) {
            FakePlayer player = new FakePlayer(account.getPlayerName());
            manager.getAccounts().put(player, account);
            manager.getCurrency().put(player,account.getAmount());
            log("§aConta do "+account.getPlayerName()+ " com saldo de "+ account.getAmount());
        }
        manager.reloadTop();
        new BukkitRunnable(){

            @Override
            public void run() {
                getSqlManager().runUpdatesQueue();
            }
        }.runTaskTimerAsynchronously(this,20,20);


    }


    @Override
    public void onDisable() {
        save();
        super.onDisable();
    }


}
