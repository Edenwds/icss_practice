package com.icss.biz;

public class Truck extends Moto {

	private double priceEachDun;
	private int dun;
	
	public Truck(String mno, MotoType mtype, int seatCount, int dun) {
		super(mno, mtype, seatCount);
		this.dun = dun;
	}

	public double getPriceEachDun() {
		return priceEachDun;
	}

	public void setPriceEachDun(double priceEachDun) {
		this.priceEachDun = priceEachDun;
	}

	public int getDun() {
		return dun;
	}

	public void setDun(int dun) {
		this.dun = dun;
	}
	/**
	 * 重写 日租价格
	 */
	public double getDayMoney() {
		return priceEachDun*dun;
	}
	
	

}
