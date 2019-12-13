package net.eduard.economy.manager;

import net.eduard.api.lib.manager.CurrencyManager;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.api.server.CoinSystem;

public class EconomyManager extends CurrencyManager implements CoinSystem {

	public EconomyManager(){

	}

	public void addCoins(FakePlayer player, double amount) {
		addBalance(player, amount);

	}

	public void removeCoins(FakePlayer player, double amount) {
		removeBalance(player, amount);

	}
	public boolean hasCoins(FakePlayer player, double amount) {
		return containsBalance(player, amount);

	}

	public double getCoins(FakePlayer player) {
		return getBalance(player);
	}

	public void setCoins(FakePlayer player, double amount) {
		setBalance(player, amount);
	}

}
