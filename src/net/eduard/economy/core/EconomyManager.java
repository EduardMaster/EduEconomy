package net.eduard.economy.core;

import net.eduard.api.lib.game.FakePlayer;
import net.eduard.api.lib.manager.CurrencyManager;

import java.util.ArrayList;
import java.util.List;

public class EconomyManager extends CurrencyManager {

    private List<PlayerEconomyAccount> accounts = new ArrayList();

    public PlayerEconomyAccount getAccount(FakePlayer player) {
        for (PlayerEconomyAccount account : accounts) {
            if (account.getPlayerName().equalsIgnoreCase(player.getName())) {
                return account;
            }

        }
        return null;
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
}
