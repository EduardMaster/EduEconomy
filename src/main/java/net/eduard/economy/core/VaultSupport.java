package net.eduard.economy.core;

import java.util.List;
import java.util.stream.Collectors;

import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.EduEconomy;
import org.bukkit.OfflinePlayer;

import net.eduard.api.lib.modules.Extra;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class VaultSupport implements Economy {
	public EconomyResponse bankBalance(String bankName) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(bankName);
		return new EconomyResponse(0, m.getBalance(fake), ResponseType.SUCCESS, null);

	}

	public EconomyResponse bankDeposit(String bankName, double amount) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(bankName);
		return new EconomyResponse(amount, m.getBalance(fake), ResponseType.SUCCESS, null);
	}

	public EconomyResponse bankHas(String bankName, double amount) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(bankName);
		if (m.getBalance(fake) >= amount)
			return new EconomyResponse(amount, m.getBalance(fake), ResponseType.SUCCESS, null);
		return new EconomyResponse(amount, m.getBalance(fake), ResponseType.FAILURE, null);
	}

	public EconomyResponse bankWithdraw(String bankName, double amount) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(bankName);
		return new EconomyResponse(amount, m.getBalance(fake), ResponseType.SUCCESS, null);
	}

	public boolean hasBank(String bankName) {
		return true;
	}

	public EconomyResponse createBank(String bankName, String bankOwner) {
			return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);

	}

	public EconomyResponse createBank(String bankName, OfflinePlayer player) {
			return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);
	}

	public boolean createPlayerAccount(String playerName) {
		return true;
	}

	public boolean createPlayerAccount(OfflinePlayer player) {
		return true;
	}

	public boolean createPlayerAccount(String playerName, String worldName) {
		return true;
	}

	public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
		return true;
	}

	public String currencyNamePlural() {
		return "Coins";
	}

	public String currencyNameSingular() {
		return "Coin";
	}

	public EconomyResponse deleteBank(String bankName) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(bankName);
			m.getCurrency().remove(fake);
			return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);

	}

	public EconomyResponse depositPlayer(String playerName, double amount) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(playerName);
		m.addBalance(fake, amount);
		return new EconomyResponse(amount, m.getBalance(fake), ResponseType.SUCCESS, null);
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(player);
		m.addBalance(fake, amount);
		return new EconomyResponse(amount, m.getBalance(fake), ResponseType.SUCCESS, null);
	}

	public EconomyResponse depositPlayer(String playerName, String wourldName, double amount) {
		return depositPlayer(playerName, amount);
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, String wourldName, double amount) {
		return depositPlayer(player, amount);
	}

	public String format(double amount) {
		return Extra.MONEY.format(amount);
	}

	public int fractionalDigits() {
		return 0;
	}

	public double getBalance(String playerName) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(playerName);
		return m.getBalance(fake);
	}

	public double getBalance(OfflinePlayer player) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(player);
		return m.getBalance(fake);
	}

	public double getBalance(String playerName, String worldName) {
		return getBalance(playerName);
	}

	public double getBalance(OfflinePlayer player, String worldName) {
		return getBalance(player);
	}

	public List<String> getBanks() {
		return EduEconomy.getInstance().getManager()
				.getCurrency()
				.entrySet()
				.stream()
				.map(entry -> entry.getKey().getName())
				.collect(Collectors.toList());
	}

	public boolean has(String playerName, double amount) {
		return getBalance(playerName) >= amount;
	}

	public boolean has(OfflinePlayer player, double amount) {
		return getBalance(player) >= amount;
	}

	public boolean has(String playerName, String worldName, double amount) {
		return getBalance(playerName) >= amount;
	}

	public boolean has(OfflinePlayer player, String worldName, double amount) {
		return getBalance(player) >= amount;
	}

	public boolean hasAccount(String playerName) {
		return true;
	}

	public boolean hasAccount(OfflinePlayer player) {
		return true;
	}

	public boolean hasAccount(String playerName, String worldName) {
		return true;
	}

	public boolean hasAccount(OfflinePlayer player, String worldName) {
		return true;
	}

	public boolean hasBankSupport() {
		return true;
	}

	public EconomyResponse isBankMember(String bankName, String playerName) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public EconomyResponse isBankMember(String bankName, OfflinePlayer player) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public EconomyResponse isBankOwner(String bankName, String playerName) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public EconomyResponse isBankOwner(String bankName, OfflinePlayer player) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public boolean isEnabled() {
		return EduEconomy.getInstance().isEnabled();
	}

	public EconomyResponse withdrawPlayer(String playerName, double amount) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(playerName);
		m.removeBalance(fake, amount);
		return new EconomyResponse(amount, m.getBalance(fake), ResponseType.SUCCESS, null);
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
		EconomyManager m = EduEconomy.getInstance().getManager();
		FakePlayer fake = new FakePlayer(player);
		m.removeBalance(fake, amount);
		return new EconomyResponse(amount, m.getBalance(fake), ResponseType.SUCCESS, null);
	}

	public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
		return withdrawPlayer(playerName, amount);
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
		return withdrawPlayer(player, amount);
	}

	
	public String getName() {
		
		return "EduEconomy";
	}

}
