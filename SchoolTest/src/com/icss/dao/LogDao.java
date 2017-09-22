package com.icss.dao;

import java.sql.PreparedStatement;

import com.icss.entity.TLog;

public class LogDao extends BaseDao {

	public void addLog(TLog log) throws Exception{
		String sql = "insert into tlog values(seq_log.nextval,?,?,?)";
		this.openConnection();
	    PreparedStatement ps = this.con.prepareStatement(sql);
	    ps.setString(1, log.getOperator());
	    ps.setTimestamp(2, new java.sql.Timestamp(log.getPotime().getTime()));
	    ps.setString(3, log.getInfo());
	    ps.executeUpdate();
	}
}
