package com.icss.biz;

import java.util.ArrayList;
import java.util.List;

import com.icss.entity.Auto;
import com.icss.entity.SaleRecord;

public class Company {

	private String  name ;          //公司名称
	private List<Auto> autos;    //公司所有待售车辆
	private List<Saler> salers;   //公司所有销售人员
	private List<SaleRecord> allRecords;
	
	public Company(String name){
		this.name = name;
		this.autos = new ArrayList<Auto>();
		this.salers = new ArrayList<Saler>();
		this.allRecords = new ArrayList<SaleRecord>();
	}

	public String getName() {
		return name;
	}

	public List<Auto> getAutos() {
		return autos;
	}

	public List<Saler> getSalers() {
		return salers;
	}
	
	/**
	 * 添加销售员
	 * @param saler
	 */
	public void addSaler(Saler saler){
		salers.add(saler);   //添加销售人员的同时 设置销售人员的所属公司
		saler.setCompany(this);
	}

	/**
	 * 销售员离职  移除
	 * @param saler
	 */
	public void removeSaler(Saler saler){
		salers.remove(saler);
	}
	
	/**
	 * 添加车辆
	 * @param auto
	 */
	public void addAuto(Auto auto){
		autos.add(auto);
	}
	
    /**
     * 添加销售记录	
     * @param saleRecord
     */
	public void addRecord(SaleRecord saleRecord){
		allRecords.add(saleRecord);
	}

	/**
	 * 查询销售记录
	 * @return
	 */
	public List<SaleRecord> getAllRecords() {
		return allRecords;
	}
	
	
}
