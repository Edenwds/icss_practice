package com.icss.dao;

import java.sql.PreparedStatement;

import com.icss.entity.User;

public class UserDao extends BaseDao {

	public void addUser(User user) throws Exception {
		String sql = "insert into suser values(?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2, user.getSno());
		ps.setString(3,user.getPwd());
		ps.setInt(4, user.getRole());
		ps.setString(5, user.getTel());
		ps.executeUpdate();
		ps.close();
	}

	
}
