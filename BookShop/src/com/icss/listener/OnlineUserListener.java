package com.icss.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 在线用户监听器 统计当前访问页面的人数
 * @author wds
 *
 */
public class OnlineUserListener implements HttpSessionListener {

	private Integer usercount = 0;
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println(arg0.getSession().getId() + "-----进入系统----hashcode="+this.hashCode());
		usercount = usercount + 1;
		System.out.println("当前在线人数：" + usercount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		usercount = usercount - 1;
		System.out.println(arg0.getSession().getId() + "退出系统");
		System.out.println("当前在线人数："+ usercount);
	}

}
