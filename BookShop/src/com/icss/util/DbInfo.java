package com.icss.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DbInfo {

	private static DbInfo dbInfo; //static修饰 只初始化一次
	private String dbDriver;
	private String dbURL;
	private String username;
	private String password;
	private DbInfo(){}
	
	public static DbInfo instance() throws Exception{
		if(dbInfo == null){
			dbInfo = new DbInfo();
			dbInfo.init();
		}
		return dbInfo;
	}
	
	private void init() throws Exception{
		Properties prop = new Properties();
		String path = DbInfo.class.getResource("/").toURI().getPath() + "db.properties"; // 使用反射 获取文件绝对路径以'/'开始
		prop.load(new FileInputStream(new File(path)));
		this.dbDriver = prop.getProperty("dbDriver");
		this.dbURL = prop.getProperty("dbURL");
		this.username = prop.getProperty("username");
		this.password = prop.getProperty("password");
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public String getDbURL() {
		return dbURL;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
}
