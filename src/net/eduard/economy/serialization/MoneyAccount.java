package net.eduard.economy.serialization;

import java.io.Serializable;

public class MoneyAccount implements Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String owner;
	private long creation;
	private double balance;

	public String getOwner() {
		return owner;
	}
	public synchronized void deposit(double amount) {
		balance+=amount;
	}
	public synchronized void withdraw(double amount) {
		balance-=amount;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreation() {
		return creation;
	}

	public void setCreation(long creation) {
		this.creation = creation;
	}

}
