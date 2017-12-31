package com.my.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.my.entity.TAdmin;

public class AdminDao extends BaseDao {
	/**
	 * 后台管理登录
	 * @param aname
	 * @param apwd
	 * @return
	 * @throws Exception 
	 */
	public TAdmin login(String aname, String apwd) throws Exception {
		TAdmin admin = null;
		String sql = "select * from tadmin  where aname=? and apwd=?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, aname);
		ps.setString(2, apwd);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			admin = new TAdmin();
			admin.setAname(rs.getString("aname"));
			admin.setApwd(rs.getString("apwd"));
		}
		return admin;
	}
}
