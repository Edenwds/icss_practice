package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.my.util.DbInfo;


public class BaseDao {

	public static final String DB_URL = "java:comp/env/jdbc/OracleBookShop"; //使用连接池的路径
	protected Connection con;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	/**
	 * 使用dbcp连接池
	 * @param strJNDIName
	 */
	protected void openConnection(String strJNDIName){
			try {
				if(this.con == null || this.con.isClosed()){
					InitialContext context = new InitialContext();
					DataSource ds = (DataSource) context.lookup(strJNDIName.trim());
					con = ds.getConnection();
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * 打开数据库连接
	 * 
	 * @throws Exception
	 */
	public void openConnection() throws Exception {
		if(this.con == null || this.con.isClosed()){
			DbInfo dbinfo = DbInfo.instance();
			Class.forName(dbinfo.getDbDriver());
			con = DriverManager.getConnection(
					dbinfo.getDbURL(), dbinfo.getUsername(), dbinfo.getPassword());
		}
	}

	public void beginTransaction() throws Exception {
		this.openConnection();
		if(this.con != null){
			this.con.setAutoCommit(false);
		}
	}
	
	public void commit() throws Exception{
		if(this.con != null){
			this.con.commit();
		}
	}
	
	public void rollback() throws Exception{
		if(this.con != null){
			this.con.rollback();
		}
	}
	public void closeConnection() {
		if (con != null) {
			try {
				this.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//翻页的sql

	/**
	 * 得到所有记录数
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int getSqlCount(String sql) throws Exception {
		int allRows = 0;
		String nsql = "select count(*) rcount from ("+sql+")";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(nsql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			allRows = rs.getInt("rcount");
		}
		rs.close();
		ps.close();
		
		return allRows;
	}

	/**
	 * 获得用于翻页的sql
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public String getTurnPageSql(String sql, int start, int end) {
		String nsql = "select * from (select rownum rn, tb.* from ( " + sql + " )tb) where rn>=" + start + " and rn<=" + end;
		
		return nsql;
	}
}
