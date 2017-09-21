package com.icss.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.Exception.NoSuchRecordException;
import com.icss.util.DaysUtil;

public class RentCompany {

	private String  name;
	private List<Moto> motos;
	private List<RentRecord> records;
	
	public RentCompany(String name){
		this.name = name;
		motos = new ArrayList<Moto>();
		records = new ArrayList<RentRecord>();
	}

	
	public List<RentRecord> getRecords() {
		return records;
	}


	public String getName() {
		return name;
	}

	public List<Moto> getMotos() {
		return motos;
	}
	/**
	 * 添加汽车
	 * @param moto
	 */
	public void addMoto(Moto moto){
		motos.add(moto);
	}
	/**
	 * 添加租车记录
	 * @param record
	 */
	private void addRecord(RentRecord record){
		records.add(record);
	}
	/**
	 * 查找租车记录
	 * @param moto
	 * @param client
	 * @return
	 */
	private RentRecord getRecord(Moto moto,String client){
		for(RentRecord record : records){
			if(record.getMno().equals(moto.getMno()) && record.getClientName().equals(client)){
				return record;
			}
		}
		return null;
	}
	/**
	 * 汽车租赁
	 * @param mtypes
	 * @param client
	 * @return
	 */
	public Moto rent(MotoType mtypes, String  client){
		Moto motoRent = null;
		for(Moto moto : motos){
			if(moto.getMtype().getTname().equals(mtypes.getTname())){
				motoRent = moto;
				motos.remove(moto);
				RentRecord record = new RentRecord();
				record.setClientName(client);
				record.setMno(moto.getMno());
				record.setPrice(moto.getDayMoney());
				record.setRentTime(new Date());
				addRecord(record);
				System.out.println("租赁成功，车牌："+moto.getMno()+",剩余车辆："+motos.size());
				break;
			}
		}
		return motoRent;
	}
	
	public double backMoto(Moto moto,String client) throws NoSuchRecordException{
		RentRecord record = getRecord(moto, client);
		if(record != null){
			record.setRentBack(new Date());
			Date rentTime = record.getRentTime();
			Date backTime = record.getRentBack();
			long allTime = backTime.getTime() - rentTime.getTime();
			int days = (int) (allTime/1000/3600/24+1);
//			int days = DaysUtil.getBetweenDay(rentTime, backTime);
			double tp = record.getPrice() * (days+1);
			records.remove(record);
			System.out.println(record.toString());
			return tp;
		}else{
			throw new NoSuchRecordException("信息错误，没有租车记录！");
		}
	}
}
