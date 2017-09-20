package com.icss.biz;

public class Family {

	private String name;
	private double account;
	private Father father;
	private Mother mother;
	private Son son;
	
	public Family(String name){
		this.name = name;
	}

	
	public Father getFather() {
		return father;
	}


	public void setFather(Father father) {
		this.father = father;
		father.setFamily(this);
	}


	public Mother getMother() {
		return mother;
	}


	public void setMother(Mother mother) {
		this.mother = mother;
		mother.setFamily(this);
	}


	public Son getSon() {
		return son;
	}


	public void setSon(Son son) {
		this.son = son;
		son.setFamily(this);
	}

	
	public String getName() {
		return name;
	}
	
	public double getAccount() {
		return account;
	}


	/**
	 * 充值或消费
	 * @param account
	 */
	public void setAccount(double account) {
		this.account = this.account + account;
	}
	
}
