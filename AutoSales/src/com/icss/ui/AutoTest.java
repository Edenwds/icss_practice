package com.icss.ui;

import java.util.List;

import com.icss.biz.Company;
import com.icss.biz.Saler;
import com.icss.consts.AutoTypeName;
import com.icss.entity.Auto;
import com.icss.entity.AutoType;
import com.icss.entity.Client;
import com.icss.entity.SaleRecord;

public class AutoTest {
	
	public static void saleTest(){
		//创建公司信息
		Company company = new Company("达达车行");
		
		//创建销售员
		Saler s1 = new Saler("张三","s001");
		Saler s2 = new Saler("李四","s002");
		Saler s3 = new Saler("王五","s003");
		
		//将销售员添加到公司
		company.addSaler(s1);
		company.addSaler(s2);	
		company.addSaler(s3);
		
		//创建车辆型号信息
        AutoType  ky = new AutoType(AutoTypeName.KAI_YUE, 95000);
        AutoType  jw = new AutoType(AutoTypeName.JUN_WEI,70000);
		
        //创建车辆
        Auto k1 = new Auto(ky,"k001");
        Auto k2 = new Auto(ky,"k002");
        Auto k3 = new Auto(ky,"k003");
        
        Auto j1 = new Auto(jw,"j001");
        Auto j2 = new Auto(jw,"j002");
        Auto j3 = new Auto(jw,"j003");
        
        //将车辆添加到公司
        company.addAuto(k1);
        company.addAuto(k2);
        company.addAuto(k3);
        company.addAuto(j1);
        company.addAuto(j2);
        company.addAuto(j3);
        
        //创建顾客
        Client c1 = new Client("小明");
        Client c2 = new Client("小华");
        
        //销售行为
        s1.sale(k1, c1);
        s2.sale(j2, c2);
        
        //获取销售记录
        List<SaleRecord> allRecords = company.getAllRecords();
        for(SaleRecord rd : allRecords){
        	System.out.println(rd.toString());
        }
        
	}
	public static void main(String[] args) {
		AutoTest.saleTest();
	}
}
