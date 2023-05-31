package com.account;

public class Account {
	private int accountNumber;
	private int pin;
	private double balance;

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Account(int accountNumber, int pin, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getPin() {
		return pin;
	}

	public double getBalance() {
		return balance;
	}
}
