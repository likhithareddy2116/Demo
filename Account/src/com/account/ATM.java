package com.account;

public class ATM {
	private double balance;

	public ATM() {
		balance = 0;
	}

	public double getbalance() {
		return balance;
	}

	public double checkBalance() {
		return balance;
	}

	/**
	 * 
	 * deposit the money
	 */
	public void deposit(Account currentAccount, double amount) {
		if (amount > 0) {
			double balance2 = currentAccount.getBalance();
			balance = balance2 + amount;
			System.out.println("Deposit successful!");
		} else {
			System.out.println("Invalid deposit amount.");
		}
	}

	/**
	 * 
	 * withdraw money
	 */
	public void withdraw(Account currentAccount, double amount) {
		if (amount > balance) {
			System.out.println("Insufficient balance.");
		} else if (amount <= 0) {
			System.out.println("Invalid withdrawal amount.");
		} else {
			balance -= amount;
			System.out.println("Withdrawal successful!");
		}
	}
}
