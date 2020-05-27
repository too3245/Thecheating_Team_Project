package com.payment.dto;

public class AccTotalDTO {
	
	private int seq_total;
	private String name;
	private String account_num;
	private int balance;
	private int stats;
	private String reg_date;
	private String recent_use;
	private int bank_name;
	private int etc1;
	private String etc2;
	
	public int getSeq_total() {
		return seq_total;
	}
	public void setSeq_total(int seq_total) {
		this.seq_total = seq_total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
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
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getRecent_use() {
		return recent_use;
	}
	public void setRecent_use(String recent_use) {
		this.recent_use = recent_use;
	}
	public int getBank_name() {
		return bank_name;
	}
	public void setBank_name(int bank_name) {
		this.bank_name = bank_name;
	}
	public int getEtc1() {
		return etc1;
	}
	public void setEtc1(int etc1) {
		this.etc1 = etc1;
	}
	public String getEtc2() {
		return etc2;
	}
	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}
	

}
