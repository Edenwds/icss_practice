package com.icss.biz;

public class MotoType {

	private double dayMoney;
	private String tname;
	
	public double getDayMoney() {
		return dayMoney;
	}
	public void setDayMoney(double dayMoney) {
		this.dayMoney = dayMoney;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public  MotoType(String tname) {
		// TODO Auto-generated constructor stub
		this.tname = tname;
	}
	
}
