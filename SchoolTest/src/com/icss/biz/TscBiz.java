package com.icss.biz;

import com.icss.dao.LogDao;
import com.icss.dao.TscDao;
import com.icss.entity.TLog;
import com.icss.entity.TStuCourse;
import com.icss.util.LogTime;

public class TscBiz {

	/**
	 * 添加学生选课记录
	 * @param tsc
	 * @throws Exception
	 */
	public void addTsc(TStuCourse tsc) throws Exception{
		TscDao dao = new TscDao();
		dao.beginTransaction();
		try {
			dao.addTsc(tsc);
			addTscLog(tsc,dao);
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
			throw e;
		}finally{
			dao.closeConnection();
		}
	}

	/**
	 * 添加学生选课日志记录
	 * @param tsc
	 * @param dao
	 * @throws Exception
	 */
	private void addTscLog(TStuCourse tsc, TscDao dao) throws Exception {
		LogDao ldao = new LogDao();
		ldao.setCon(dao.getCon());
		TLog log = new TLog();
		log.setOperator("adimn");
		log.setPotime(LogTime.getTimestamp());
		String info = "添加学生课程表----  "+tsc.getSno()+"++"+tsc.getKno();
		log.setInfo(info);
		ldao.addLog(log);
	}
	/**
	 * 根据学号和课程号判断是否已有记录
	 * @param sno
	 * @param kno
	 * @return
	 * @throws Exception
	 */
	public TStuCourse getCourse(String sno, String kno) throws Exception{
		TscDao dao = new TscDao();
		TStuCourse tsc = null;
		dao.beginTransaction();
		try {
			tsc = dao.getCourse(sno, kno);
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		
		return tsc;
	}
}
