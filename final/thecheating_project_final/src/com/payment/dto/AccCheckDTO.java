package com.payment.dto;

public class AccCheckDTO {

	private int seq_check;
	private int bank_name;
	private String account_num;
	private String holder;
	private int balance;
	private int stats;
	private String val_date;
	public int getSeq_check() {
		return seq_check;
	}
	public void setSeq_check(int seq_check) {
		this.seq_check = seq_check;
	}
	public int getBank_name() {
		return bank_name;
	}
	public void setBank_name(int bank_name) {
		this.bank_name = bank_name;
	}
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getStats() {
		return stats;
	}
	public void setStats(int stats) {
		this.stats = stats;
	}
	public String getVal_date() {
		return val_date;
	}
	public void setVal_date(String val_date) {
		this.val_date = val_date;
	}
	
}
