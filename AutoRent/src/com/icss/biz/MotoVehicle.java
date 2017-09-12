package com.icss.biz;

public abstract class MotoVehicle {

	protected String No;
	protected String Brand;      //品牌
	protected String color;        
	protected long Mileage;     //里程
	
	
	
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getNo() {
		return No;
	}


	public String getBrand() {
		return Brand;
	}


	public long getMileage() {
		return Mileage;
	}


	public abstract double CalRent( int days);
}
