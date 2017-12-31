package com.my.biz;

import com.my.dao.AdminDao;
import com.my.entity.TAdmin;

public class AdminBiz {

	/**
	 * 后台管理登录
	 * @param aname
	 * @param apwd
	 * @return
	 * @throws Exception 
	 */
	public TAdmin login(String aname, String apwd) throws Exception {
		TAdmin admin = null;
		AdminDao dao = new AdminDao();
		try {
			admin = dao.login(aname, apwd);
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		return admin;
	}
	
}
