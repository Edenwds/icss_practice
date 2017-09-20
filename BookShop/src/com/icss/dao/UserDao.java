package com.icss.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icss.entity.User;

public class UserDao extends BaseDao {
	
	/**
	 * 判断用户名是否重复
	 * @param uname
	 * @return
	 * @throws Exception 
	 */
	public boolean isSameUserName(String uname) throws Exception{
		boolean flag = false;
		String sql = "select * from tuser where uname=?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, uname);
		ResultSet res = ps.executeQuery();
		if(res.next()){
			flag = true;
		}
		res.close();
		ps.close();
		return flag;
	}
	
	/**
	 * 普通用户注册
	 * @param user
	 * @throws Exception 
	 */
	public void regist(User user) throws Exception{
		String sql  = "insert into tuser values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);	
		ps.setString(1, user.getUname());
		ps.setString(2, user.getPwd());
		ps.setInt(3, user.getRole());
		ps.setString(4, user.getTel());
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * 用户登录
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception 
	 */
	public User login(String uname,String pwd) throws Exception{
        User user=null;
        String sql = "select * from tuser where uname=? and pwd=?";
        this.openConnection();
        PreparedStatement ps = this.con.prepareStatement(sql);
        ps.setString(1, uname);
        ps.setString(2, pwd);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
        	user = new User();
        	user.setUname(uname);
        	user.setPwd(pwd);
        	user.setRole(rs.getInt("role"));
        	user.setTel(rs.getString("tel"));
        }
        rs.close();
        ps.close();
        
        return user;
	}


	/**
	 * 得到所有用户的信息
	 * @return
	 * @throws Exception
	 */
	public List<User> getAllUser() throws Exception {
		String sql = "select * from tuser";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<User> list = new ArrayList<User>();
		while(rs.next()){
			User user = new User();
			user.setUname(rs.getString("uname"));
			user.setPwd(rs.getString("pwd"));
			user.setRole(rs.getInt("role"));
			user.setTel(rs.getString("tel"));
			list.add(user);
		}
		rs.close();
		ps.close();
		return list;
	}

	/**
	 * 删除用户
	 * @param uname
	 * @throws Exception
	 */
	public void deleteUser(String uname) throws Exception {
		String sql = "delete from tuser where uname=?";    //拼接sql语句时，?前后都不能有空格
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, uname);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		String sql = "update tuser set pwd=?,tel=? where uname=?";
		this.openConnection();
		PreparedStatement ps =this.con.prepareStatement(sql);
		ps.setString(1, user.getPwd());
		ps.setString(2, user.getTel());
		ps.setString(3, user.getUname());
		ps.executeUpdate();
		ps.close();
	}

    /**
     * 查找用户信息
     * @param uname
     * @return
     * @throws Exception
     */
	public User findUser(String uname) throws Exception {
		String sql = "select * from tuser where uname=?";
	    this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, uname);
		ResultSet rs = ps.executeQuery();
		User user = null;
		while(rs.next()){
			user = new User();
			user.setUname(rs.getString("uname"));
			user.setPwd(rs.getString("pwd"));
			user.setRole(rs.getInt("role"));
			user.setTel(rs.getString("tel"));
		}
		rs.close();
		ps.close();
		
		return user;
	}
	
}
