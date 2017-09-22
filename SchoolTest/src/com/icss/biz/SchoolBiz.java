package com.icss.biz;

import java.util.List;

import com.icss.dao.SchoolDao;
import com.icss.dto.CStudent;
import com.icss.exceptions.InputNullException;

public class SchoolBiz {

	/**
	 * 查询某个班级的所有学生
	 * @param cno
	 * @return
	 * @throws Exception 
	 */
	public List<CStudent> getAllStu(String cno) throws Exception{
		List<CStudent> allStu = null;
		if(!cno.equals("")){
			SchoolDao dao = new SchoolDao();
			try {
				allStu = dao.getAllStu(cno);
			} catch (Exception e) {
				throw e;
			}finally{
				dao.closeConnection();
			}
		}else{
			throw new InputNullException("入参为空");
		}
		return allStu;
	}
}
