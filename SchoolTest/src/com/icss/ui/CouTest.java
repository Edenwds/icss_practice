package com.icss.ui;

import java.util.ArrayList;
import java.util.List;

import com.icss.biz.CouBiz;
import com.icss.entity.TCourse;

public class CouTest {

	public static void addCourse(){
		CouBiz biz = new CouBiz();
		TCourse c1 = new TCourse();
		c1.setKname("语文");
		c1.setKno("k001");
		try {
			biz.addCourse(c1);
			System.out.println("添加课程成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addBatchCou(){
		CouBiz biz = new CouBiz();
		List<TCourse> cous = new ArrayList<TCourse>();	
		TCourse c1 = new TCourse();
		c1.setKname("数学");
		c1.setKno("k002");
		TCourse c2 = new TCourse();
		c2.setKname("英语");
		c2.setKno("k003");
		cous.add(c1);
		cous.add(c2);
		try {
			biz.addBatchCou(cous);
			System.out.println("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		CouTest.addCourse();
		CouTest.addBatchCou();
	}
}
