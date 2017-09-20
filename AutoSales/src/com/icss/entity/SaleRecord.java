package com.icss.entity;

import java.util.Date;


public class SaleRecord {

	private String salerName;      //销售员姓名
	private String clientName;    //顾客姓名
	private Date saleDate;           //销售日期
	private String autono;           //汽车编号
	private double price;            //成交价格
	
	
	public String getSalerName() {
		return salerName;
	}
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public String getAutono() {
		return autono;
	}
	public void setAutono(String autono) {
		this.autono = autono;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaleRecord [ 销售日期：" + saleDate +" 销售员："+ salerName+"  顾客： "+ clientName+
				"  车辆编号："+autono+"  成交价格："+price+ " ]";
	}
	
	
}
