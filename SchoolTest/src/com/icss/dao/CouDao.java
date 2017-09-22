package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.icss.entity.TCourse;

public class CouDao extends BaseDao {

	/**
	 * 添加课程
	 * @param course
	 * @throws Exception
	 */
	public void addCourse(TCourse course) throws Exception {
		String sql = "insert into tcourse values(?,?)";
		this.openConnection();
		PreparedStatement  ps = this.con.prepareStatement(sql);
		ps.setString(1, course.getKname());
		ps.setString(2, course.getKno());
		ps.executeUpdate();
		ps.close();
	}

	public TCourse getCou(String kno) throws Exception {
		String sql = "select * from tcourse where kno=?";
		TCourse cou = null;
	    this.openConnection();
	    PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, kno);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			cou = new TCourse();
			cou.setKname(rs.getString("kname"));
			cou.setKno(rs.getString("kno"));
		}
		
		return cou;
	}

}
