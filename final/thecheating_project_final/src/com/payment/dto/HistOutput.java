package com.payment.dto;

public class HistOutput {
	private int seq_output;
	private int seq_user;
	private String user_account;
	private String output_account;
	private int withdraw;
	private String o_resdate;
	private String reciever;
	private int balance;
	private int stats;
	private String memo;
	
	public int getSeq_output() {
		return seq_output;
	}
	public void setSeq_output(int seq_output) {
		this.seq_output = seq_output;
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
	public String getOutput_account() {
		return output_account;
	}
	public void setOutput_account(String output_account) {
		this.output_account = output_account;
	}
	public int getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}
	public String getO_resdate() {
		return o_resdate;
	}
	public void setO_resdate(String o_resdate) {
		this.o_resdate = o_resdate;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
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
