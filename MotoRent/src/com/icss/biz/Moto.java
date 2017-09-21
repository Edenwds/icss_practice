package com.icss.biz;

public abstract class Moto {
	private String mno;
	private int seatCount;
	private MotoType mtype;
	private double dayMoney;
	
	public Moto(String mno, MotoType  mtype, int seatCount){
		this.mno = mno;
		this.seatCount = seatCount;
		this.mtype = mtype;
	}

	
	public double getDayMoney() {
		return this.mtype.getDayMoney();
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public MotoType getMtype() {
		return mtype;
	}

	public void setMtype(MotoType mtype) {
		this.mtype = mtype;
	}

	public String getMno() {
		return mno;
	}
	
	

}
