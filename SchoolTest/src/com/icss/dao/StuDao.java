package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.entity.TStudent;

public class StuDao extends BaseDao {
	
	/**
	 * 添加学生
	 * @param ts
	 * @throws Exception
	 */
	public void addStu(TStudent ts) throws Exception {
		String sql = "insert into tstudent values(?,?,?,?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, ts.getSname());
		ps.setString(2, ts.getSno());
		ps.setString(3, ts.getCno());
		ps.setString(4, ts.getSex());
		ps.setString(5, ts.getAddress());
		ps.setString(6, ts.getTel());
		ps.setDate(7, new java.sql.Date( ts.getBirthday().getTime()));
		ps.setDouble(8, ts.getHeight());
		ps.setInt(9, ts.getState());
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 根据学号查找学生
	 * @param sno
	 * @return
	 * @throws Exception
	 */
	public TStudent getStu(String sno) throws Exception {
		String sql = "select * from tstudent where sno=?";
		TStudent stu = null;
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, sno);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			stu = new TStudent();
			stu.setSname(rs.getString("sname"));
			stu.setSno(rs.getString("sno"));
			stu.setCno(rs.getString("cno"));
			stu.setSex(rs.getString("sex"));
			stu.setAddress(rs.getString("address"));
			stu.setTel(rs.getString("tel"));
			stu.setBirthday(rs.getDate("birthday"));
			stu.setHeight(rs.getDouble("height"));
			stu.setState(rs.getInt("state"));
		}
		rs.close();
		ps.close();
		
		return stu; 
	}

	/**
	 * 删除学生
	 * @param sno
	 * @throws Exception
	 */
	public void deleteStu(String sno) throws Exception {
		String sql = "delete from tstudent where sno=?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, sno);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 更新学生状态 
	 * @param i
	 * @param sno
	 * @throws Exception
	 */
	public void updateSta(int i, String sno) throws Exception {
		String sql = "update tstudent set state=? where sno=?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setInt(1, i);
		ps.setString(2, sno);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 批量添加学生
	 * @param cno
	 * @param stu
	 * @throws Exception
	 */
	public void addBatchStus(String cno, TStudent stu) throws Exception {
		String sql = "insert into tstudent values(?,?,?,?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, stu.getSname());
		ps.setString(2, stu.getSno());
		ps.setString(3, cno);
		ps.setString(4, stu.getSex());
		ps.setString(5, stu.getAddress());
		ps.setString(6, stu.getTel());
		ps.setDate(7, new java.sql.Date( stu.getBirthday().getTime()));
		ps.setDouble(8, stu.getHeight());
		ps.setInt(9, stu.getState());
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 查询符合条件的学生
	 * @param beginDate
	 * @param endDate
	 * @throws SQLException 
	 */
	public List<TStudent> getStudents(Date beginDate, Date endDate) throws Exception {
		List<TStudent> allStu = null;
		String sql = "select * from tstudent where birthday >? and birthday <?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date(beginDate.getTime()));
		ps.setDate(2, new java.sql.Date(endDate.getTime()));
		ResultSet rs = ps.executeQuery();
		allStu = new ArrayList<TStudent>();
		while(rs.next()){
			TStudent stu = new TStudent();
			stu.setSname(rs.getString("sname"));
			stu.setSno(rs.getString("sno"));
			stu.setCno(rs.getString("cno"));
			stu.setSex(rs.getString("sex"));
			stu.setAddress(rs.getString("address"));
			stu.setTel(rs.getString("tel"));
			stu.setBirthday(rs.getDate("birthday"));
			stu.setHeight(rs.getDouble("height"));
			stu.setState(rs.getInt("state"));
			allStu.add(stu);
		}
		rs.close();
		ps.close();
		
		return allStu;
	}

}
