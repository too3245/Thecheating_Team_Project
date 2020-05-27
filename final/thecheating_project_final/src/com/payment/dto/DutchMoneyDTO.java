package com.payment.dto;

public class DutchMoneyDTO {
	private int seq_dutch;
	private int chat_idx;
	private String chat_leader;
	private String pay_type;
	private String sender_idx;
	private int deposit;
	private int target;
	private int current_raise;
	private String depo_date;
	private String pay_date;
	private int stats;
	
	public int getSeq_dutch() {
		return seq_dutch;
	}
	public void setSeq_dutch(int seq_dutch) {
		this.seq_dutch = seq_dutch;
	}
	public int getChat_idx() {
		return chat_idx;
	}
	public void setChat_idx(int chat_idx) {
		this.chat_idx = chat_idx;
	}
	public String getChat_leader() {
		return chat_leader;
	}
	public void setChat_leader(String chat_leader) {
		this.chat_leader = chat_leader;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getSender_idx() {
		return sender_idx;
	}
	public void setSender_idx(String sender_idx) {
		this.sender_idx = sender_idx;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getCurrent_raise() {
		return current_raise;
	}
	public void setCurrent_raise(int current_raise) {
		this.current_raise = current_raise;
	}
	public String getDepo_date() {
		return depo_date;
	}
	public void setDepo_date(String depo_date) {
		this.depo_date = depo_date;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public int getStats() {
		return stats;
	}
	public void setStats(int stats) {
		this.stats = stats;
	}
	
}
