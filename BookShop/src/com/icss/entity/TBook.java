package com.icss.entity;

import java.util.Date;

public class TBook {

	private String isbn;
	private String bname;
	private String author;
	private Date pudate;
	private String press;
	private double price;
	private String descr;
	private byte[] pic;
	private int buycount;
	private int bkcount;  //库存数量
	
	
	public int getBkcount() {
		return bkcount;
	}
	public void setBkcount(int bkcount) {
		this.bkcount = bkcount;
	}
	public int getBuycount() {
		return buycount;
	}
	public void setBuycount(int buycount) {
		this.buycount = buycount;
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
	public Date getPudate() {
		return pudate;
	}
	public void setPudate(Date pudate) {
		this.pudate = pudate;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	
}
