package net.eduard.economy.manager;

import java.util.List;

import org.bukkit.OfflinePlayer;

import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.Main;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class EconomyVaultSupport implements Economy {
	public EconomyResponse bankBalance(String bankName) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);

	}

	public EconomyResponse bankDeposit(String bankName, double amount) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public EconomyResponse bankHas(String bankName, double amount) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public EconomyResponse bankWithdraw(String bankName, double amount) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public boolean hasBank(String bankName) {
		return false;
	}

	public EconomyResponse createBank(String bankName, String bankOwner) {
		

		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public EconomyResponse createBank(String bankName, OfflinePlayer player) {
	
		
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
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
	
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, null);
	}

	public EconomyResponse depositPlayer(String playerName, double amount) {
		FakePlayer fake = new FakePlayer(playerName);
		Main.getInstance().getManager().addBalance(fake, amount);

		return new EconomyResponse(amount, Main.getInstance().getManager().getBalance(fake), ResponseType.SUCCESS, null);
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
		FakePlayer fake = new FakePlayer(player);
		Main.getInstance().getManager().addBalance(fake, amount);
		return new EconomyResponse(amount,  Main.getInstance().getManager().getBalance(fake), ResponseType.SUCCESS, null);
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
		return Main.getInstance().getManager().getBalance(new FakePlayer(playerName));
	}

	public double getBalance(OfflinePlayer player) {
		return Main.getInstance().getManager().getBalance(new FakePlayer(player));
	}

	public double getBalance(String playerName, String worldName) {
		return getBalance(playerName);
	}

	public double getBalance(OfflinePlayer player, String worldName) {
		return getBalance(player);
	}

	public List<String> getBanks() {
		return null;
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
		return false;
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
		return Main.getInstance().isEnabled();
	}

	public EconomyResponse withdrawPlayer(String playerName, double amount) {
		FakePlayer fake = new FakePlayer(playerName);
		Main.getInstance().getManager().removeBalance(fake, amount);

		return new EconomyResponse(amount, Main.getInstance().getManager().getBalance(fake), ResponseType.SUCCESS, null);
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
		FakePlayer fake = new FakePlayer(player);
		Main.getInstance().getManager().removeBalance(fake, amount);

		return new EconomyResponse(amount, Main.getInstance().getManager().getBalance(fake), ResponseType.SUCCESS,
				null);
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
