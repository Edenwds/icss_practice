package com.icss.biz;

import java.util.Date;

public class RentRecord {
	private String clientName;   // 客户
	private Date rentTime;
	private String mno;              //租车信息
	private double price;
	private Date rentBack;
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Date getRentTime() {
		return rentTime;
	}
	public void setRentTime(Date rentTime) {
		this.rentTime = rentTime;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getRentBack() {
		return rentBack;
	}
	public void setRentBack(Date rentBack) {
		this.rentBack = rentBack;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RentRecord [客户姓名=" + clientName + ", 租车日期="
				+ rentTime + ", 车牌号=" + mno + ", 日租价格=" + price
				+ ", 还车日期=" + rentBack + "]";
	}	
}
