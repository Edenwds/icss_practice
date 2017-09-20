package com.icss.ui;

import com.icss.biz.Family;
import com.icss.biz.Father;
import com.icss.biz.Mother;
import com.icss.biz.Son;

public class Test {

	public static void pcTest(){
		Family family = new Family("三口之家");
		Father father = new Father("father");
		Mother mother = new Mother("mother");
		Son son = new Son("son");
		family.setFather(father);
		family.setMother(mother);
		family.setSon(son);
		new Thread(new FatherThread(father)).start();
		new Thread(new MotherThread(mother)).start();
		new Thread(new SonThread(son)).start();
	}
	public static void main(String[] args) {
		Test.pcTest();
	}
}

class FatherThread implements Runnable{

	private Father father;
	public FatherThread(Father father){
		this.father = father;
	}
	public void run() {
			while(true){
				this.father.earning(20);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}
	
}

class MotherThread implements Runnable{

	private Mother mother;
	public MotherThread(Mother mother){
		this.mother = mother;
	}
	@Override
	public void run() {
		while(true){
				this.mother.shopping(20);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
}

class SonThread implements Runnable{

	private Son son;
	public SonThread(Son son){
		this.son = son;
	}
	public void run() {
			while(true){
				this.son.play(10);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
}