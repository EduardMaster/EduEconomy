package net.eduard.economy.core;

import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.api.lib.manager.CurrencyManager;
import net.eduard.economy.EduEconomy;

import java.util.*;
import java.util.stream.Collectors;

public class EconomyManager extends CurrencyManager {


    private List<PlayerEconomyAccount> top = new LinkedList<>();

    public List<PlayerEconomyAccount> getTop() {
        return top;
    }

    public Map<FakePlayer, PlayerEconomyAccount> getAccounts() {
        return accounts;
    }

    final transient private Map<FakePlayer, PlayerEconomyAccount> accounts = new HashMap<>();


    public PlayerEconomyAccount getAccount(FakePlayer player) {
        PlayerEconomyAccount account = accounts.get(player);
        if (account == null) {
            account = new PlayerEconomyAccount();
            account.setAmount(getInicialAmount());
            account.setPlayerName(player.getName());

            EduEconomy.getInstance().getSqlManager().insertData(account);


        }
        return account;
    }

    public void reloadTop(){
        top = accounts.values().stream().sorted((c1,c2) ->
                Double.compare(c2.getAmount() , c1.getAmount()))
                .collect(Collectors.toList());

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

    public void clearAccounts() {
        accounts.clear();
        getCurrency().clear();
    }


}
