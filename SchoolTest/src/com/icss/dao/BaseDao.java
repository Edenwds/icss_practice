package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {


	protected Connection con;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	/**
	 * 打开数据库连接
	 * 
	 * @throws Exception
	 */
	public void openConnection() throws Exception {
		if(this.con == null || this.con.isClosed()){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.0.19.252:1521:orcl", "WDS", "wds123");
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
}
