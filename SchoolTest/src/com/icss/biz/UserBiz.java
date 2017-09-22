package com.icss.biz;

import com.icss.dao.UserDao;
import com.icss.entity.User;

public class UserBiz {

	/**
	 * 添加用户
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception{
		UserDao dao = new UserDao();
		dao.beginTransaction();
		try {
			dao.addUser(user);
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
			throw e;
		}finally{
			dao.closeConnection();
		}
	}
}
