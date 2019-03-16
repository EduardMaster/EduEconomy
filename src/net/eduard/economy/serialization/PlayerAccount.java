package net.eduard.economy.serialization;

import java.io.Serializable;

import net.eduard.api.lib.modules.FakePlayer;

public class PlayerAccount implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long accountId;
	private FakePlayer player;
	private double balance;
	private long creation;
	private int transactionsAmount;

	public FakePlayer getPlayer() {
		return player;
	}

	public synchronized void deposit(double amount) {
		balance += amount;
	}

	public synchronized void withdraw(double amount) {
		balance -= amount;
	}

	public void setPlayer(FakePlayer player) {
		this.player = player;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getTransactionsAmount() {
		return transactionsAmount;
	}

	public void setTransactionsAmount(int transactionsAmount) {
		this.transactionsAmount = transactionsAmount;
	}

	public long getCreation() {
		return creation;
	}

	public void setCreation(long creation) {
		this.creation = creation;
	}

}
