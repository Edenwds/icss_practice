package com.icss.biz;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.icss.dao.CouDao;
import com.icss.dao.LogDao;
import com.icss.entity.TCourse;
import com.icss.entity.TLog;
import com.icss.exceptions.InputNullException;
import com.icss.util.LogTime;

public class CouBiz {
	
	/**
	 * 批量添加课程
	 * @param cous
	 * @throws Exception
	 */
	public void addBatchCou(List<TCourse> cous) throws InputNullException, Exception{
		if(cous == null || cous.size() == 0){
			CouDao dao = new CouDao();
			try {
				dao.beginTransaction();
				for(TCourse tc : cous){
						dao.addCourse(tc);
				}
				addBatchCoLog(cous,dao);
				dao.commit();
			} catch (Exception e) {
					dao.rollback();
					throw e;
			}finally{
					dao.closeConnection();
			}
		}else{
			throw new InputNullException("集合为空");
		}
	}
	private void addBatchCoLog(List<TCourse> cous, CouDao dao) throws Exception {
		LogDao ldao = new LogDao();
		ldao.setCon(dao.getCon());
		TLog log = new TLog();
		log.setOperator("adimn");
		log.setPotime(LogTime.getTimestamp());
		StringBuilder sb = new StringBuilder();
		sb.append("批量添加课程  ");
		for(TCourse tc : cous){
			sb.append(tc.getKname()+"-"+tc.getKno()+"   ");
		}
		log.setInfo(sb.toString());
		ldao.addLog(log);
	}
	/**
	 * 添加课程
	 * @param course
	 * @throws Exception
	 */
	public void addCourse(TCourse course) throws Exception{
		CouDao dao = new CouDao();
		try {
			dao.beginTransaction();
			dao.addCourse(course);
			addCoLog(course, dao);
			dao.commit();
		} catch (Exception e) {
		    throw e;
		}finally{
			dao.closeConnection();
		}
		
	}

	/**
	 * 添加课程时添加日志记录
	 * @param course
	 * @param dao
	 * @throws ParseException
	 * @throws Exception
	 */
	private void addCoLog(TCourse course, CouDao dao) throws Exception{
		
		LogDao ldao = new LogDao();
		ldao.setCon(dao.getCon());
		TLog log = new TLog();
		log.setOperator("adim");
	    log.setPotime(new Date());
		String info = "添加课程--  "+course.getKname()+"  课程编号："+course.getKno();
		log.setInfo(info);
		ldao.addLog(log);
	}
	
	/**
	 * 根据课程号查找课程
	 * @param cno
	 * @return
	 * @throws Exception
	 */
	public TCourse getCou(String cno) throws Exception{
		CouDao dao = new CouDao();
		dao.beginTransaction();
		TCourse cou = null;
		try {
			cou = dao.getCou(cno);
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		
		return cou;
	}

}
