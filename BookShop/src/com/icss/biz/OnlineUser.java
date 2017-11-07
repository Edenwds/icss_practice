package com.icss.biz;

import java.util.HashMap;
import java.util.Map;

import com.icss.entity.BUser;

public class OnlineUser {
	private static Map<String,BUser> users;
	public static Map<String, BUser> getUsers(){
		return users;
	}
	static{
		users = new HashMap<String,BUser>();
	}
	public static void addUser(String sessionid, BUser user){
		users.put(sessionid, user);
	}
	public static void removeUser(String sessionid){
		users.remove(sessionid);
	}
}
