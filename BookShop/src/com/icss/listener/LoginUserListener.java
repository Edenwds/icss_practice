package com.icss.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.icss.biz.OnlineUser;
import com.icss.entity.BUser;

public class LoginUserListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//System.out.println(se.getSession().getId()+"---进入系统----hashcode=" + this.hashCode());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(se.getSession().getId()+"--退出系统");
		BUser user = OnlineUser.getUsers().get(se.getSession().getId());
		if(user != null){
			System.out.println("退出用户是："+ user.getUname());
			OnlineUser.removeUser(se.getSession().getId());
			System.out.println("当前登录在线人数为："+ OnlineUser.getUsers().size());
		}
	}

}
