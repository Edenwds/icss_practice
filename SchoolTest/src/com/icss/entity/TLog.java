package com.icss.entity;

import java.util.Date;

public class TLog {

	private int aid;
	private String operator;
	private Date potime;
	private String info;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getPotime() {
		return potime;
	}
	public void setPotime(Date potime) {
		this.potime = potime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
