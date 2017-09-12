package com.icss.biz;

public class Car extends MotoVehicle {

	private CarType  carType;  //型号
	
	public Car(String no,CarType carType){
		this.No = no;
		this.carType = carType;
	}
	
	
	public CarType getCarType() {
		return carType;
	}


	@Override
	public double CalRent(int days) {
		return carType.getRentPrice()*days;
	}

}
