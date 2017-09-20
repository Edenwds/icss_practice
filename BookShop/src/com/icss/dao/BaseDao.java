package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

	protected Connection con;

	/**
	 * 打开数据库连接
	 * 
	 * @throws Exception
	 */
	public void openConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@10.0.19.252:1521:orcl", "WDS", "wds123");
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
