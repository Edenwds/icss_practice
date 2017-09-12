package com.icss.ui;

import com.icss.biz.Bus;
import com.icss.biz.Car;
import com.icss.biz.CarType;
import com.icss.consts.BrandName;
import com.icss.consts.CarTypeName;

public class Test {

	public static void rentTest(){
		// 创建车型信息
		CarType  ct1 = new CarType(CarTypeName.BK_GL,600);	
		CarType  ct2 = new CarType(CarTypeName.BK_LY, 300);
		CarType  ct3 = new CarType(CarTypeName.BM, 500);
		
		//创建车辆
		Car c1 = new Car("001", ct1);
		Car c2 = new Car("002", ct2);
		Car c3 = new Car("003", ct3);
		
		Bus b1 = new Bus("100", 10);	
		b1.setBrand(BrandName.JB);
		Bus b2 = new Bus("101", 20);
		b2.setBrand(BrandName.JL);
		
		double tp = c1.CalRent(5);
		System.out.println(c1.getCarType().getName()+"租五天的价格："+tp);
		double tp2 = c2.CalRent(7);
		System.out.println(c2.getCarType().getName()+"租七天的价格："+tp2);
		
		double tp3 = b1.CalRent(1);
		System.out.println(b1.getBrand()+b1.getSeatCount()+"座，租1天价格："+tp3);

		double tp4 = b2.CalRent(3);
		System.out.println(b2.getBrand()+b2.getSeatCount()+"座，租3天价格："+tp4);
	}

	
	public static void main(String[] args) {
		Test.rentTest();
	}
}
