package net.eduard.economy.serialization;

import java.io.Serializable;

public class MoneyTransaction implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long accountId;
	private long date;
	private double amount;

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
}
