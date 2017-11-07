package com.icss.dto;

import java.util.Date;

public class Buyinfo {
	 private String uname;     //用户名
	 private String oid;           //订单编号
	 private double allmoney;
	 private Date paytime;
	 private int aid;                //订单明细编号
	 private int buycount;
	 private double dealprice;
	 private String isbn;         // 书的编号
	 private String bname;    
	 private String author;
	 private Date pubdate;
	 private String press; 
	 private int bkcount;       // 库存数量
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public double getAllmoney() {
		return allmoney;
	}
	public void setAllmoney(double allmoney) {
		this.allmoney = allmoney;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getBuycount() {
		return buycount;
	}
	public void setBuycount(int buycount) {
		this.buycount = buycount;
	}
	public double getDealprice() {
		return dealprice;
	}
	public void setDealprice(double dealprice) {
		this.dealprice = dealprice;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public int getBkcount() {
		return bkcount;
	}
	public void setBkcount(int bkcount) {
		this.bkcount = bkcount;
	}
	 
	 
}
