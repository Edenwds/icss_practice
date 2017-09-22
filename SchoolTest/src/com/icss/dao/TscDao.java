package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.icss.entity.TStuCourse;

public class TscDao extends BaseDao {

	public void addTsc(TStuCourse tsc) throws Exception {
		String sql = "insert into tstucourse values(seq_tsc.nextval,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, tsc.getSno());
		ps.setString(2, tsc.getKno());
		ps.executeUpdate();
		ps.close();
	}

	public TStuCourse getCourse(String sno, String kno) throws Exception {
		String sql = "select * from tstucourse where sno=? and kno=?";
		TStuCourse tsc = null;
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, sno);
		ps.setString(2, kno);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			tsc = new TStuCourse();
			tsc.setKno(rs.getString("kno"));
			tsc.setSno(rs.getString("sno"));
		}
		
		return tsc;
	}

	
}
