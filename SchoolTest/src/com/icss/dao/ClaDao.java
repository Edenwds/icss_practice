package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.icss.entity.TClass;

public class ClaDao extends BaseDao{

	/**
	 * 添加班级
	 * @param clazz
	 * @throws Exception
	 */
	public void addClass(TClass clazz) throws Exception {
		String sql = "insert into TClass values(?,?)";
        this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, clazz.getCname());
		ps.setString(2, clazz.getCno());
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 查找班级
	 * @param cno
	 * @return
	 * @throws Exception
	 */
	public TClass finClass(String cno) throws Exception {
		String sql = "select * from tclass where cno=?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, cno);
		ResultSet rs = ps.executeQuery();
		TClass clazz = null;
	    while(rs.next()){
	    	clazz = new TClass();
	    	clazz.setCname(rs.getString("cname"));
	    	clazz.setCno(rs.getString("cno"));
	    }
	    rs.close();
	    ps.close();
	    
		return clazz;
	}

}
