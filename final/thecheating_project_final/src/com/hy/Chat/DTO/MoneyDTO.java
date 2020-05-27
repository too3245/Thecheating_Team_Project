package com.hy.Chat.DTO;

public class MoneyDTO {
	private int moneyIdx;
	private String moneyRoom;
	private String moneyTitle;
	private int leftMoney;
	
	public int getLeftMoney() {
		return leftMoney;
	}
	public void setLeftMoney(int leftMoney) {
		this.leftMoney = leftMoney;
	}
	public int getMoneyIdx() {
		return moneyIdx;
	}
	public void setMoneyIdx(int moneyIdx) {
		this.moneyIdx = moneyIdx;
	}
	public String getMoneyRoom() {
		return moneyRoom;
	}
	public void setMoneyRoom(String moneyRoom) {
		this.moneyRoom = moneyRoom;
	}
	public String getMoneyTitle() {
		return moneyTitle;
	}
	public void setMoneyTitle(String moneyTitle) {
		this.moneyTitle = moneyTitle;
	}
	
}
