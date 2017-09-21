package com.icss.ui;

import com.icss.Exception.NoSuchRecordException;
import com.icss.biz.Bus;
import com.icss.biz.Car;
import com.icss.biz.Moto;
import com.icss.biz.MotoBrand;
import com.icss.biz.MotoType;
import com.icss.biz.RentCompany;
import com.icss.biz.Truck;

public class Test {
	
	public static void rentTest() {
		RentCompany company = new RentCompany("达达租赁");
		
		//创建车型信息
		MotoType type1 = new MotoType(MotoBrand.BAOMA_550);
		type1.setDayMoney(500);
		MotoType type2 = new MotoType(MotoBrand.BIEKE_LIN);
		type2.setDayMoney(300);
		MotoType type3 = new MotoType(MotoBrand.BIEKE_SHANGWU);
		type3.setDayMoney(600);
		MotoType type4 = new MotoType(MotoBrand.JINBEI_11);
		type4.setDayMoney(800);
		MotoType type5 = new MotoType(MotoBrand.JIN_LONG_54);
		type5.setDayMoney(1500);
		MotoType truck = new MotoType(MotoBrand.BEN_CHI_ZHONG);
		
		Bus b1 = new Bus("b001",type4,11);
		Bus b2 = new Bus("b002",type5,54);
		
		Car c1 = new Car("c001",type1);
		Car c2 = new Car("c002",type2);
		Car c3 = new Car("c003",type3);
		
		Truck t1 = new Truck("t001",truck,5,30);
		t1.setPriceEachDun(50);
		
		company.addMoto(b1);
		company.addMoto(b2);
		company.addMoto(c1);
		company.addMoto(c2);
		company.addMoto(c3);
		company.addMoto(t1);
		
//		Moto moto = company.rent(type1, "张三");
//		if(moto != null){
//			System.out.println("张三租赁了一辆车 "+moto.getMtype().getTname()+"--车牌号:"+moto.getMno());
//		}else{
//			System.out.println("租赁失败");
//		}
		
		Moto moto = company.rent(truck, "李四");
		if(moto != null){
			System.out.println("李四 租了一辆重卡" + moto.getMtype().getTname() +"车牌号为: "+moto.getMno());
		}else{
			System.out.println("租赁失败");
		}
				
		try {
			double tp = company.backMoto(moto, "李四");
			System.out.println("总费用为："+tp);
			System.out.println("还车成功！");
		} catch (NoSuchRecordException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	
	}
	
	public static void main(String[] args) {
		Test.rentTest();
	}

}
