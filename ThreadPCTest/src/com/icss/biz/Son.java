package com.icss.biz;

public class Son {

	private String name;
	private Family family;
	
	public Son(String name){
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
	
	public void play(double money){
		synchronized(this.family){
			double left = this.family.getAccount();
			System.out.println("消费前，家庭余额为："+left);
			if(left >= money){
				this.family.setAccount(-money);
				System.out.println("消费后，家庭余额为："+this.family.getAccount());
			}else{
				System.out.println(this.name+"暂停消费");
			}
		}		
	}
}
