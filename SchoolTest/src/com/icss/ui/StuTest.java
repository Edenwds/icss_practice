package com.icss.ui;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.biz.StuBiz;
import com.icss.entity.TStudent;
import com.icss.exceptions.NoSuchClassException;

public class StuTest {

	/**
	 * 查找符合条件(某个时间段)的学生测试 
	 */
	public static void getStus(){
		StuBiz biz = new StuBiz();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d1 = "1996-1-1";
		String d2 = "1996-12-30";
		Date beginDate = null;
		Date endDate = null;
	    try {
			 beginDate = format.parse(d1);
			 endDate = format.parse(d2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    List<TStudent> allStu = null;
	    try {
			 allStu = biz.getStudents(beginDate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    if(allStu != null && allStu.size() != 0){
	    	for(TStudent stu : allStu){
	    		System.out.println(stu.toString());
	    	}
	    }
	}
	/**
	 * 学生批量选课测试
	 */
	public static void selectBatchCou(){
		StuBiz biz = new StuBiz();
		try {
			TStudent stu = biz.getStu("20148888");
			List<String> knos = new ArrayList<String>();
			knos.add("k002");
			knos.add("k003");
			biz.selectBatchCou(stu, knos);
			System.out.println("选课成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * 批量添加学生测试
	 */
	public static void addBatchStus(){
		StuBiz biz = new StuBiz();
		TStudent ts1 = new TStudent();
		ts1.setSname("xiaoliu");
		ts1.setSno("20146003");
		ts1.setSex("1");
		ts1.setAddress("昌平");
		ts1.setHeight(179);
		ts1.setState(1);
		ts1.setTel("17887778921");
		ts1.setCno("b001");
		SimpleDateFormat   format = new SimpleDateFormat("yyyy-MM-dd");
        String bir = "1996-02-03";
        Date date = null;
        try {
			 date = format.parse(bir);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		ts1.setBirthday(date);
		
		TStudent ts2 = new TStudent();
		ts2.setSname("xiaoqi");
		ts2.setSno("20146004");
		ts2.setSex("2");
		ts2.setAddress("昌平");
		ts2.setHeight(170);
		ts2.setState(2);
		ts2.setTel("15856398721");
		ts2.setCno("b001");
        String bir2 = "1996-08-03";
        Date date1 = null;
        try {
			 date1 = format.parse(bir2);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        ts2.setBirthday(date1);
		
		List<TStudent> stus = new ArrayList<TStudent> ();
		stus.add(ts1);
		stus.add(ts2);
		try {
			biz.addBatchStus("b001", stus);
			System.out.println("添加成功");
		} catch (NoSuchClassException e) {
			e.printStackTrace();
			System.out.println("没有此班级");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加失败");
		}
	}
	/**
	 * 删除学生测试
	 */
	public static void deleteStu(){
		StuBiz biz = new StuBiz();
		try {
			biz.deleteStu("20142300");
			System.out.println("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加学生测试
	 */
	public static void addStu(){
		StuBiz biz = new StuBiz();
		TStudent ts1 = new TStudent();
		ts1.setSname("zhangsan");
		ts1.setSno("20146000");
		ts1.setSex("1");
		ts1.setAddress("昌平");
		ts1.setHeight(175);
		ts1.setState(1);
		ts1.setTel("15884300021");
		ts1.setCno("b001");
		SimpleDateFormat   format = new SimpleDateFormat("yyyy-MM-dd");
        String bir = "1995-05-03";
        Date date = null;
        try {
			 date = format.parse(bir);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		ts1.setBirthday(date);
		
		try {
			biz.addStu(ts1);
			System.out.println("添加成功");
			System.out.println(ts1.toString());
		} catch (NoSuchClassException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 查找学生测试
	 */
	public static void getStu(){
		StuBiz biz = new StuBiz();
		try {
			TStudent stu = biz.getStu("20142300");
			if(stu == null){
				System.out.println("该生信息不存在");
			}else{
				System.out.println(stu.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 学生选课测试
	 */
	public static void optCou(){
		StuBiz biz = new StuBiz();
		try {
			TStudent ts = biz.getStu("20148888");
			biz.optCou(ts, "k001");
			System.out.println("选课成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	public static void main(String[] args) {
		StuTest.getStus();
//		StuTest.selectBatchCou();
//		StuTest.addBatchStus();
//		StuTest.deleteStu();
//		StuTest.addStu();
//		StuTest.getStu();
//		StuTest.optCou();
	}
}
