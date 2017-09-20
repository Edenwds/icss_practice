package com.icss.biz;

import java.util.List;

import com.icss.Exception.InputEmptyException;
import com.icss.Exception.SameUserException;
import com.icss.dao.UserDao;
import com.icss.entity.User;

public class UserBiz {

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void regist(User user) throws Exception {

		if (user != null) {
			UserDao dao = new UserDao();
			boolean isRet;
			try {
				isRet = dao.isSameUserName(user.getUname());
				if (isRet) {
					// 重名
					throw new SameUserException("用户名已使用，请修改");
				} else {
					// 不重复 注册
					dao.regist(user);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				dao.closeConnection();
			}
		} else {
			throw new InputEmptyException("入参错误，user=null");
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public User login(String uname, String pwd) throws Exception {
		User user = null;
		if (uname != null && pwd != null && !uname.equals("")
				&& !pwd.equals("")) {
			UserDao dao = new UserDao();
			try {
				user = dao.login(uname, pwd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			} finally {
				dao.closeConnection();
			}
		}
		return user;
	}

	/**
	 * 得到所有用戶信息
	 * @return
	 * @throws Exception
	 */
	public List<User> getAllUser() throws Exception {
		UserDao dao = new UserDao();
		List<User> list = null;
		try {
			list = dao.getAllUser();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		return list;
	}
	
	/**
	 * 删除某个用户
	 * @param uname
	 * @throws Exception
	 */
	public void deleteUser(String uname) throws Exception{
		UserDao dao = new UserDao();
		try {
			dao.deleteUser(uname);
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
	}
	/**
	 * 查找用户信息
	 * @param uname
	 * @return
	 * @throws Exception
	 */
	public User findUser(String uname) throws Exception{
		UserDao dao = new UserDao();
		User user = null; 
	    try {
			user=dao.findUser(uname);
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
	    return user;
	}
	
	/**
	 * 更新用户信息 
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception{
		UserDao dao = new UserDao();
		try {
			dao.updateUser(user);
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		
	}
}
