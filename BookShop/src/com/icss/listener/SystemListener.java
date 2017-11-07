package com.icss.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.icss.util.DbInfo;
import com.icss.util.Log;

public class SystemListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("关闭系统");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("系统启动");
	    try {
	    	//系统启动时DbInfo就进行初始化
			DbInfo dbinfo = DbInfo.instance();
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
		}
	}

}
