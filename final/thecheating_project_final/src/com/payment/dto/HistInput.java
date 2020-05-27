package com.payment.dto;

public class HistInput {

	private int seq_input;
	private int seq_user;
	private String user_account;
	private String input_account;
	private int deposit;
	private String i_resdate;
	private String  sender;
	private int balance;
	private int stats;
	private String memo;
	
	public int getSeq_input() {
		return seq_input;
	}
	public void setSeq_input(int seq_input) {
		this.seq_input = seq_input;
	}
	public int getSeq_user() {
		return seq_user;
	}
	public void setSeq_user(int seq_user) {
		this.seq_user = seq_user;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getInput_account() {
		return input_account;
	}
	public void setInput_account(String input_account) {
		this.input_account = input_account;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getI_resdate() {
		return i_resdate;
	}
	public void setI_resdate(String i_resdate) {
		this.i_resdate = i_resdate;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
