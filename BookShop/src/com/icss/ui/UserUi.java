package com.icss.ui;

import java.util.List;

import com.icss.Exception.InputEmptyException;
import com.icss.Exception.SameUserException;
import com.icss.biz.UserBiz;
import com.icss.entity.User;
import com.icss.enums.IRole;

public class UserUi {

	/**
	 * 用户注册测试
	 */
	public static void registerTest(){
		UserBiz biz = new UserBiz();
		User user = new User();
		user.setUname("rose");
		user.setPwd("123");
		user.setTel("12343254321");
		user.setRole(IRole.COMMON_USER);
		try {
			biz.regist(user);
			System.out.println(user.getUname()+" 注册成功");
		} catch (SameUserException e) {
			System.out.println(e.getMessage());
		} catch (InputEmptyException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("网络忙,请稍后重试");
		}
	}
	
	/**
	 * 用户登录测试
	 */
	public static void loginTest(){
          UserBiz biz = new UserBiz();
          User user;
		try {
			user = biz.login("rose", "123");
			if(user != null){
				System.out.println("登录成功！");
				if(user.getRole() == 1){
					System.out.println(user.getUname()+"是管理员");
				}else if(user.getRole() == 2){
					System.out.println(user.getUname()+"是普通用户");
				}
			}else{
				System.out.println("登录失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("网络资源繁忙，请和管理员联系");
		}
	}
	/**
	 * 得到所有用戶信息测试
	 */
	public static void getAllUserTest(){
		UserBiz biz = new UserBiz();
		try {
			List<User> list = biz.getAllUser();
			if(list != null && list.size() != 0){
				for(User user : list){
					System.out.println(user.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 删除用户测试
     */
	public static void deleteUserTest(){
		UserBiz biz = new UserBiz();
		try {
			biz.deleteUser("jack");
			System.out.println("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 更新用户信息测试
	 */
	public static void updateUserTest(){
		UserBiz biz = new UserBiz();
		try {
			User user = biz.findUser("rose");
			if(user !=null){
				user.setPwd("321");
				user.setTel("1999921032");
			}else{
				System.out.println("用户不存在");
			}
			biz.updateUser(user);
			System.out.println("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	public static void main(String[] args) {
//		UserUi.updateUserTest();
//		UserUi.deleteUserTest();
//		UserUi.getAllUserTest();
//		UserUi.loginTest();
		UserUi.registerTest();
//		Test test =  new Test("aaaa");
	}
}
