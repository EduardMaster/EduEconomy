package net.eduard.economy.serialization;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.OfflinePlayer;

import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.EduEconomy;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class VaultSupport implements Economy {
	public EconomyResponse bankBalance(String bankName) {
		MoneyAccount bank = AccountsManager.getInstance().getMoneyAccount(bankName);

		return new EconomyResponse(0, bank.getBalance(), ResponseType.SUCCESS, null);

	}

	public EconomyResponse bankDeposit(String bankName, double amount) {
		MoneyAccount bank = AccountsManager.getInstance().getMoneyAccount(bankName);
		bank.deposit(amount);
		return new EconomyResponse(amount, bank.getBalance(), ResponseType.SUCCESS, null);
	}

	public EconomyResponse bankHas(String bankName, double amount) {
		MoneyAccount bank = AccountsManager.getInstance().getMoneyAccount(bankName);
		if (bank.getBalance() >= amount)
			return new EconomyResponse(amount, bank.getBalance(), ResponseType.SUCCESS, null);
		return new EconomyResponse(amount, bank.getBalance(), ResponseType.FAILURE, null);
	}

	public EconomyResponse bankWithdraw(String bankName, double amount) {
		MoneyAccount bank = AccountsManager.getInstance().getMoneyAccount(bankName);
		bank.withdraw(amount);
		return new EconomyResponse(amount, bank.getBalance(), ResponseType.SUCCESS, null);
	}

	public boolean hasBank(String bankName) {
		return AccountsManager.getInstance().getMoneyAccount(bankName) != null;
	}

	public EconomyResponse createBank(String bankName, String bankOwner) {
		if (hasBank(bankName)) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, null);
		} else {
			AccountsManager.getInstance().createBank(bankName, bankOwner);
			return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);
		}

	}

	public EconomyResponse createBank(String bankName, OfflinePlayer player) {
		if (hasBank(bankName)) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, null);
		} else {
			AccountsManager.getInstance().createBank(bankName, player.getName());
			return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);
		}

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
		MoneyAccount bank = AccountsManager.getInstance().getMoneyAccount(bankName);
		if (bank == null) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, null);
		} else {
			AccountsManager.getInstance().getAccounts().remove(bank);
			return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);
		}

	}

	public EconomyResponse depositPlayer(String playerName, double amount) {
		PlayerAccount playerAccount = AccountsManager.getInstance().getPlayerAccount(new FakePlayer(playerName));
		playerAccount.deposit(amount);

		return new EconomyResponse(amount, playerAccount.getBalance(), ResponseType.SUCCESS, null);
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
		PlayerAccount playerAccount = AccountsManager.getInstance().getPlayerAccount(new FakePlayer(player));
		playerAccount.deposit(amount);

		return new EconomyResponse(amount, playerAccount.getBalance(), ResponseType.SUCCESS, null);
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
		return AccountsManager.getInstance().getPlayerAccount(new FakePlayer(playerName)).getBalance();
	}

	public double getBalance(OfflinePlayer player) {
		return AccountsManager.getInstance().getPlayerAccount(new FakePlayer(player)).getBalance();
	}

	public double getBalance(String playerName, String worldName) {
		return getBalance(playerName);
	}

	public double getBalance(OfflinePlayer player, String worldName) {
		return getBalance(player);
	}

	public List<String> getBanks() {
		return AccountsManager.getInstance().getAccounts().stream().map(account -> account.getName())
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
		return EduEconomy.getInstance().isEnabled();
	}

	public EconomyResponse withdrawPlayer(String playerName, double amount) {
		PlayerAccount playerAccount = AccountsManager.getInstance().getPlayerAccount(new FakePlayer(playerName));
		playerAccount.withdraw(amount);

		return new EconomyResponse(amount, playerAccount.getBalance(), ResponseType.SUCCESS, null);
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
		PlayerAccount playerAccount = AccountsManager.getInstance().getPlayerAccount(new FakePlayer(player));
		playerAccount.withdraw(amount);

		return new EconomyResponse(amount, playerAccount.getBalance(), ResponseType.SUCCESS, null);
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
