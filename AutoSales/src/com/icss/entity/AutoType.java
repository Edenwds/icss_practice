package com.icss.entity;

public class AutoType {

	private String name;          //型号名称
	private double price;         //价格
	
	public AutoType(String name,double price){
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
