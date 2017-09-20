package com.icss.entity;


public class Auto {

	private String  ano;                      //编号
	private String  color;                   //颜色
	private AutoType autoType;      //型号
	private boolean isSaled;            //销售状态
	
	public Auto(AutoType autoType, String ano){
		this.autoType = autoType;
		this.ano = ano;
		this.isSaled = false;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isSaled() {
		return isSaled;
	}

	public void setSaled(boolean isSaled) {
		this.isSaled = isSaled;
	}

	public String getAno() {
		return ano;
	}

	public AutoType getAutoType() {
		return autoType;
	}
	
	
}
