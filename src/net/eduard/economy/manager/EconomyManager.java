package net.eduard.economy.manager;

import java.util.List;

import org.bukkit.OfflinePlayer;

import net.eduard.api.lib.manager.CurrencyManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.api.server.CoinSystem;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class EconomyManager extends CurrencyManager implements Economy, CoinSystem {

	public EconomyResponse bankBalance(String bankName) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse bankDeposit(String bankName, double amount) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse bankHas(String bankName, double amount) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse bankWithdraw(String bankName, double amount) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse createBank(String bankName, String amount) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse createBank(String bankName, OfflinePlayer player) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public boolean createPlayerAccount(String playerName) {
		return false;
	}

	public boolean createPlayerAccount(OfflinePlayer player) {
		return false;
	}

	public boolean createPlayerAccount(String playerName, String worldName) {
		return false;
	}

	public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
		return false;
	}

	public String currencyNamePlural() {
		return "Coins";
	}

	public String currencyNameSingular() {
		return "Coin";
	}

	public EconomyResponse deleteBank(String bankName) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse depositPlayer(String playerName, double amount) {
		addBalance(new FakePlayer(playerName), amount);
		EconomyResponse res = new EconomyResponse(amount, 0, ResponseType.SUCCESS, null);
		return res;
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
		addBalance(new FakePlayer(player), amount);
		EconomyResponse res = new EconomyResponse(amount, 0, ResponseType.SUCCESS, null);
		return res;
	}

	public EconomyResponse depositPlayer(String playerName, String wourldName, double amount) {
		addBalance(new FakePlayer(playerName), amount);
		EconomyResponse res = new EconomyResponse(amount, 0, ResponseType.SUCCESS, null);
		return res;
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, String wourldName, double amount) {
		addBalance(new FakePlayer(player), amount);
		EconomyResponse res = new EconomyResponse(amount, 0, ResponseType.SUCCESS, null);
		return res;
	}

	public String format(double amount) {
		return Extra.MONEY.format(amount);
	}

	public int fractionalDigits() {
		return 0;
	}

	public double getBalance(String playerName) {
		return getBalance(new FakePlayer(playerName));
	}

	public double getBalance(OfflinePlayer player) {
		return getBalance(new FakePlayer(player));
	}

	public double getBalance(String playerName, String worldName) {
		return getBalance(new FakePlayer(playerName));
	}

	public double getBalance(OfflinePlayer player, String worldName) {
		return getBalance(new FakePlayer(player));
	}

	public List<String> getBanks() {
		return null;
	}

	public boolean has(String playerName, double amount) {
		return containsBalance(new FakePlayer(playerName), amount);
	}

	public boolean has(OfflinePlayer player, double amount) {
		return containsBalance(new FakePlayer(player), amount);
	}

	public boolean has(String playerName, String worldName, double amount) {
		return containsBalance(new FakePlayer(playerName), amount);
	}

	public boolean has(OfflinePlayer player, String worldName, double amount) {
		return containsBalance(new FakePlayer(player), amount);
	}

	public boolean hasAccount(String playerName) {
		return getCurrency().containsKey(new FakePlayer(playerName));
	}

	public boolean hasAccount(OfflinePlayer player) {
		return getCurrency().containsKey(new FakePlayer(player));
	}

	public boolean hasAccount(String playerName, String worldName) {
		return getCurrency().containsKey(new FakePlayer(playerName));
	}

	public boolean hasAccount(OfflinePlayer player, String worldName) {
		return getCurrency().containsKey(new FakePlayer(player));
	}

	public boolean hasBankSupport() {
		return false;
	}

	public EconomyResponse isBankMember(String bankName, String playerName) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse isBankMember(String bankName, OfflinePlayer player) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse isBankOwner(String bankName, String playerName) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public EconomyResponse isBankOwner(String bankName, OfflinePlayer player) {
		EconomyResponse res = new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
		return res;
	}

	public boolean isEnabled() {
		return true;
	}

	public EconomyResponse withdrawPlayer(String playerName, double amount) {
		removeBalance(new FakePlayer(playerName), amount);
		EconomyResponse res = new EconomyResponse(amount, 0, ResponseType.SUCCESS, null);
		return res;
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
		removeBalance(new FakePlayer(player), amount);
		EconomyResponse res = new EconomyResponse(amount, 0, ResponseType.SUCCESS, null);
		return res;
	}

	public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
		removeBalance(new FakePlayer(playerName), amount);
		EconomyResponse res = new EconomyResponse(amount, 0, ResponseType.SUCCESS, null);
		return res;
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
		removeBalance(new FakePlayer(player), amount);
		EconomyResponse res = new EconomyResponse(amount, 0, ResponseType.SUCCESS, null);
		return res;
	}

	public void addCoins(FakePlayer player, double amount) {
		addBalance(player, amount);

	}

	public void removeCoins(FakePlayer player, double amount) {
		removeCoins(player, amount);

	}

	public double getCoins(FakePlayer player) {
		return getBalance(player);
	}

	public void setCoins(FakePlayer player, double amount) {
		setBalance(player, amount);
	}

}
