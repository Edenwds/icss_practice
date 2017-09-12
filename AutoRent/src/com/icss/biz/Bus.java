package com.icss.biz;

public class Bus extends MotoVehicle {

	private int seatCount;  //座位数
	private double bigPrice;
	private double smallPrice;
	public Bus(String no, int seatCount){
		this.No = no;
		this.seatCount = seatCount;
		this.bigPrice = 1500;
		this.smallPrice = 800;
	}
	
	public int getSeatCount() {
		return seatCount;
	}

	public String getBrand(){
		return this.Brand;
	}
	
	public void setBrand(String brand){
		this.Brand = brand;
	}
	
	public double getBigPrice() {
		return bigPrice;
	}


	public void setBigPrice(double bigPrice) {
		this.bigPrice = bigPrice;
	}


	public double getSmallPrice() {
		return smallPrice;
	}


	public void setSmallPrice(double smallPrice) {
		this.smallPrice = smallPrice;
	}


	@Override
	public double CalRent(int days) {
		// TODO Auto-generated method stub
		if(seatCount <= 16 ){
			return smallPrice * days;
		}else{
			return bigPrice * days;
		}
	}

}
