package com.icss.biz;

public class Father {

	private String name;
	private Family family;
	
	public Father(String name){
		this.name = name;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public String getName() {
		return name;
	}
	
	public void earning(double money){
		synchronized(this.family){
			double left = this.family.getAccount();
			System.out.println("充值前，家庭余额为："+left);
			this.family.setAccount(money);
			System.out.println("充值后，家庭余额为："+this.family.getAccount());
		}
	}
}
