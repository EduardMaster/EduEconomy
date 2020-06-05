package net.eduard.economy.core;

import net.eduard.api.lib.game.FakePlayer;
import net.eduard.api.lib.manager.CurrencyManager;
import net.eduard.economy.EduEconomy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EconomyManager extends CurrencyManager {

    volatile transient boolean saving = false;

    transient private Map<FakePlayer, PlayerEconomyAccount> accounts = new HashMap<>();

    public PlayerEconomyAccount getAccount(FakePlayer player) {
        PlayerEconomyAccount account = accounts.get(player);
        if (account == null) {
            PlayerEconomyAccount newAccount = new PlayerEconomyAccount();
            newAccount.setAmount(getInicialAmount());
            newAccount.setPlayerName(player.getName());
            if (!saving) {
                accounts.put(player, newAccount);
            }

            account = newAccount;
            if (EduEconomy.getInstance().getDB().hasConnection()) {
                EduEconomy.getInstance().getSqlManager().insertData(account);
            }

        }
        return account;
    }


    public EconomyManager() {

    }

    public void addCoins(FakePlayer player, double amount) {
        addBalance(player, amount);
        getAccount(player).addAmount(amount);

    }

    public void removeCoins(FakePlayer player, double amount) {
        removeBalance(player, amount);
        getAccount(player).removeAmount(amount);

    }

    public boolean hasCoins(FakePlayer player, double amount) {
        return containsBalance(player, amount);

    }

    public double getCoins(FakePlayer player) {
        return getBalance(player);
    }

    public void setCoins(FakePlayer player, double amount) {
        setBalance(player, amount);
        getAccount(player).setAmount(amount);
    }


    public void removeAccount(PlayerEconomyAccount account) {
        accounts.remove(account);
    }

    public Collection<PlayerEconomyAccount> getAccounts() {
        return accounts.values();
    }

    public Map<FakePlayer, PlayerEconomyAccount> getAccountsMap() {
        return accounts;
    }

    public void clearAccounts() {
        accounts.clear();
        getCurrency().clear();
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }

}
