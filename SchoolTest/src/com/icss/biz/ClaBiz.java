package com.icss.biz;

import java.util.Date;

import com.icss.dao.ClaDao;
import com.icss.dao.LogDao;
import com.icss.entity.TClass;
import com.icss.entity.TLog;

public class ClaBiz {

	/**
	 * 添加班级
	 * @param clazz
	 * @throws Exception
	 */
	public void addClass(TClass clazz) throws Exception{
		ClaDao dao = new ClaDao();
		dao.beginTransaction();
		try {
			dao.addClass(clazz); 
			addClaLog(clazz, dao);
			dao.commit();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
	}
	
	/**
	 * 在添加班级时，添加日志记录
	 * @param clazz
	 * @param dao 
	 * @throws Exception 
	 */
	private void addClaLog(TClass clazz, ClaDao dao) throws Exception {
		LogDao ldao = new LogDao();
		ldao.setCon(dao.getCon());
		TLog log = new TLog();
		log.setOperator("adim");
		log.setPotime(new Date());
		String info = "添加班级--"+clazz.getCname()+"  编号："+clazz.getCno();
		log.setInfo(info);
		ldao.addLog(log);
	}

	/**
	 * 查找班级
	 * @param cno
	 * @return
	 * @throws Exception
	 */
	public TClass findClass(String cno) throws Exception{
		ClaDao dao = new ClaDao();
		TClass clazz = null;
		try {
			clazz = dao.finClass(cno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally{
			dao.closeConnection();
		}
		
		return clazz;
	}
}
