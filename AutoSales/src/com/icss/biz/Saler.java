package com.icss.biz;

import java.util.Date;
import java.util.List;

import com.icss.entity.Auto;
import com.icss.entity.Client;
import com.icss.entity.SaleRecord;

public class Saler {

	private String name;
	private String sno;                     //编号
	private Company company;     //所属公司
	
	public Saler(String name, String sno){
		this.name = name;
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public String getSno() {
		return sno;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	/**
	 * 汽车销售
	 * @param auto
	 * @param client
	 */
	public void sale(Auto auto , Client client){
		//获取该销售员所属公司的车辆信息
		List<Auto> allAuto = this.company.getAutos();
		//销售车辆 将车辆从list中移除   需判断是否为该公司车辆
		boolean flag = allAuto.remove(auto);
		if(flag){
			auto.setSaled(true);
			
			//销售成功 创建销售记录
			SaleRecord rd = new SaleRecord();
			rd.setAutono(auto.getAno());
			rd.setClientName(client.getName());
			rd.setSaleDate(new Date());
			rd.setSalerName(this.name);
			rd.setPrice(auto.getAutoType().getPrice());
			
			//将销售记录添加进list集合
			this.company.addRecord(rd);
		}
		
	}
}
