package com.icss.biz;

public class CarType {

	private String name;
	private double rentPrice;
	
	public CarType(String name, double renPrice){
		this.name = name;
		this.rentPrice = renPrice;
	}

	public double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getName() {
		return name;
	}
	
	
}
