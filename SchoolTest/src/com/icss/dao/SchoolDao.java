package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icss.dto.CStudent;

public class SchoolDao extends BaseDao {

	/**
	 * 查询某个班级的所有学生
	 * @param cno
	 * @return
	 * @throws Exception
	 */
	public List<CStudent> getAllStu(String cno) throws Exception {
		List<CStudent> allStu = null;
		String sql = "select s.*,c.cname from tstudent s,tclass c where s.cno = c.cno and c.cno=?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, cno);
		ResultSet rs = ps.executeQuery();
		allStu = new ArrayList<CStudent>();
		while(rs.next()){
			CStudent cs = new CStudent();
			cs.setSname(rs.getString("sname"));
			cs.setSno(rs.getString("sno"));
			cs.setCno(rs.getString("cno"));
			cs.setSex(rs.getString("sex"));
			cs.setAddress(rs.getString("address"));
			cs.setTel(rs.getString("tel"));
			cs.setBirthday(rs.getDate("birthday"));
			cs.setHeight(rs.getDouble("height"));
			cs.setState(rs.getInt("state"));
			cs.setCname(rs.getString("cname"));
			allStu.add(cs);
		}
		rs.close();
		ps.close();
		
		return allStu;
	}

}
