package net.eduard.economy.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import net.eduard.api.lib.modules.FakePlayer;

public class AccountsManager implements Serializable, Cloneable {

	private static AccountsManager instance;

	public static AccountsManager load(File file)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		BukkitObjectInputStream r = new BukkitObjectInputStream(new FileInputStream(file));
		AccountsManager manager = (AccountsManager) r.readObject();
		r.close();
		return manager;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long currentAccountId = 1;
	private ArrayList<PlayerAccount> playerAccounts = new ArrayList<>();
	private ArrayList<MoneyAccount> accounts = new ArrayList<>();
	private ArrayList<MoneyTransaction> transactions = new ArrayList<>();

	public AccountsManager clone() throws CloneNotSupportedException {
		return (AccountsManager) super.clone();
	}

	public List<MoneyTransaction> getTransactionsOf(PlayerAccount account) {
		return transactions.stream().filter(loopAccount -> loopAccount.getAccountId() == account.getAccountId())
				.collect(Collectors.toList());
	}

	public PlayerAccount getPlayerAccount(FakePlayer player) {
		for (PlayerAccount account : playerAccounts) {
			if (account.getPlayer().equals(player)) {
				return account;
			}

		}

		return createAccount(player);

	}

	public void save(File file) throws Exception {
		BukkitObjectOutputStream w = new BukkitObjectOutputStream(new FileOutputStream(file));
		w.writeObject(this);
		w.close();

	}

	public PlayerAccount createAccount(FakePlayer fake) {
		PlayerAccount account = new PlayerAccount();
		account.setAccountId(currentAccountId++);
		account.setPlayer(fake);
		account.setBalance(0);
		account.setTransactionsAmount(0);
		playerAccounts.add(account);
		return account;

	}

	public ArrayList<PlayerAccount> getPlayerAccounts() {
		return playerAccounts;
	}

	public void setPlayerAccounts(ArrayList<PlayerAccount> playerAccounts) {
		this.playerAccounts = playerAccounts;
	}

	public ArrayList<MoneyTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<MoneyTransaction> transactions) {
		this.transactions = transactions;
	}

	public long getCurrentAccountId() {
		return currentAccountId;
	}

	public void setCurrentAccountId(long currentAccountId) {
		this.currentAccountId = currentAccountId;
	}

	public ArrayList<MoneyAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<MoneyAccount> accounts) {
		this.accounts = accounts;
	}

	public static AccountsManager getInstance() {
		return instance;
	}

	public static void setInstance(AccountsManager instance) {
		AccountsManager.instance = instance;
	}

	public MoneyAccount getMoneyAccount(String bankName) {
		for (MoneyAccount account : accounts) {
			if (account.getName().equalsIgnoreCase(bankName)) {
				return account;
			}
		}
		return null;

	}

	public MoneyAccount createBank(String bankName, String bankOwner) {
		MoneyAccount bank = new MoneyAccount();
		bank.setCreation(System.currentTimeMillis());
		bank.setName(bankName);
		bank.setOwner(bankOwner);
		accounts.add(bank);
		return bank;

	}
}
