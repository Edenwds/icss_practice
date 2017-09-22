package com.icss.ui;

import com.icss.biz.ClaBiz;
import com.icss.entity.TClass;

public class ClazzTest {

	public static void addClassTest(){
		TClass clazz1 = new TClass();
		clazz1.setCname("一年二班");
		clazz1.setCno("b002");
	    ClaBiz biz = new ClaBiz();
	    try {
			biz.addClass(clazz1);
			System.out.println("添加班级成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void findClass(){
		ClaBiz biz = new ClaBiz();
		TClass clazz = null;
		try {
			clazz = biz.findClass("b001");
			if(clazz != null){
				System.out.println(clazz.getCname());
			}else{
				System.out.println("班级不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	public static void main(String[] args) {
		ClazzTest.addClassTest();
//		ClazzTest.findClass();
	}
}
