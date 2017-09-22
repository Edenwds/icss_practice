package com.icss.ui;

import java.util.List;

import com.icss.biz.SchoolBiz;
import com.icss.dto.CStudent;

public class SchoolTest {

	/**
	 * 得到某个班级所有学生测试
	 */
	public static void getCStudent(){
		SchoolBiz biz = new SchoolBiz();
		List<CStudent> allStu = null;
		try {
			allStu = biz.getAllStu("b002");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(allStu != null && allStu.size() != 0){
			for(CStudent cs : allStu){
				System.out.println(cs.toString());
			}
		}
	}
	public static void main(String[] args) {
		SchoolTest.getCStudent();
	}
}
