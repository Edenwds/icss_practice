package com.icss.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.contants.RoleType;
import com.icss.dao.ClaDao;
import com.icss.dao.CouDao;
import com.icss.dao.LogDao;
import com.icss.dao.StuDao;
import com.icss.dao.TscDao;
import com.icss.dao.UserDao;
import com.icss.entity.TClass;
import com.icss.entity.TCourse;
import com.icss.entity.TLog;
import com.icss.entity.TStuCourse;
import com.icss.entity.TStudent;
import com.icss.entity.User;
import com.icss.exceptions.HadTheRecException;
import com.icss.exceptions.InputNullException;
import com.icss.exceptions.NoSuchClassException;
import com.icss.exceptions.NoSuchCourseException;
import com.icss.util.LogTime;

public class StuBiz {

	/**
	 * 查询符合条件的学生
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public List<TStudent> getStudents(Date beginDate, Date endDate) throws Exception{
		List<TStudent> allStu = null;
		if(beginDate != null && endDate != null){
			StuDao dao = new StuDao();
			dao.beginTransaction();
			try {
				allStu = dao.getStudents(beginDate,endDate);
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
	/**
	 * 学生批量选课
	 * @param ts
	 * @param cous
	 * @throws Exception
	 */
	public void selectBatchCou(TStudent stu, List<String> knos) throws Exception{
		StuDao dao = new StuDao();
		CouDao cdao = new CouDao();
		List<TCourse> cous = null;
		try {
			cous = new ArrayList<TCourse>();
			dao.beginTransaction();
			cdao.setCon(dao.getCon());
			for(String kno : knos){
			    TCourse cou = cdao.getCou(kno);
			    if(cou == null){
			    	throw new NoSuchCourseException("此课程不存在");
			    }
			    addTsc(stu, cou, dao);
			    cous.add(cou);
			}
			addBatchCouLog(stu,cous,dao);
			dao.commit();
		}catch(NoSuchCourseException e){
			throw e;
		}catch(HadTheRecException e){
			throw e;
		}catch (Exception e) {
			dao.rollback();
			throw e;
		}finally{
			dao.closeConnection();
		}
	}
	/**
	 * 批量选课日志记录
	 * @param stu
	 * @param cous
	 * @param dao
	 * @throws Exception 
	 */
	private void addBatchCouLog(TStudent stu, List<TCourse> cous, StuDao dao) throws Exception {
		LogDao ldao = new LogDao();
		ldao.setCon(dao.getCon());
		TLog log = new TLog();
		log.setOperator(stu.getSname()+stu.getSno());
		log.setPotime(LogTime.getTimestamp());
		StringBuilder sb = new StringBuilder();
		sb.append("学生"+stu.getSname()+stu.getSno()+"选课：");
		for(TCourse cou : cous){
			sb.append(cou.getKname()+"--"+cou.getKno()+"  ");
		}
		log.setInfo(sb.toString());
		ldao.addLog(log);
	}
	/**
	 * 往班级批量添加学生
	 * @param cno
	 * @param stus
	 * @throws NoSuchClassException
	 * @throws Exception
	 */
	public void addBatchStus(String cno, List<TStudent> stus) throws NoSuchClassException ,Exception{
		if(cno != null && stus != null){
		StuDao dao = new StuDao();
		ClaDao cdao = new ClaDao();
		UserDao udao = new UserDao();
		try {
			dao.beginTransaction();
			cdao.setCon(dao.getCon());
			udao.setCon(dao.getCon());
			TClass clazz = cdao.finClass(cno);
			if(clazz == null ){
				throw new NoSuchClassException("该班级不存在");
			}
			for(TStudent s : stus){
				dao.addBatchStus(cno,s);
				User user = createUser(s);
				udao.addUser(user);
			}
			addBatchLog(cno, stus, dao);
			dao.commit();
		} catch(NoSuchClassException e){
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		}
	}
	/**
	 * 为学生创建用户
	 * @param s
	 * @return
	 */
	private User createUser(TStudent s) {
		User user = new User();
		user.setSno(s.getSno());
		user.setPwd("123");
		user.setRole(RoleType.COMMON);
		user.setUname(s.getSname());
		user.setTel(s.getTel());

		return user;
	}
	/**
	 * 添加批量添加学生日志
	 * @param cno
	 * @param stus
	 * @param dao
	 * @throws Exception 
	 */
	private void addBatchLog(String cno, List<TStudent> stus, StuDao dao) throws Exception {
		LogDao ldao = new LogDao();
		ldao.setCon(dao.getCon());
		TLog log = new TLog();
		log.setOperator("admin");
		log.setPotime(LogTime.getTimestamp());
		StringBuilder sb = new StringBuilder();
		for(TStudent s : stus){
			sb.append(s.getSname()+"--"+s.getSno()+"  ");
		}
		log.setInfo("批量添加学生："+sb.toString());
		ldao.addLog(log);
	}
	/**
	 * 删除学生
	 * @param sno
	 * @throws Exception
	 */
	public void deleteStu(String sno) throws Exception{
		StuDao dao = new StuDao();
		try {
			dao.beginTransaction();
			dao.deleteStu(sno);
		}catch(SQLException e){
			if(e.getSQLState().equals("23000")){
				try{
					dao.updateSta(0,sno);
					dao.commit();
				}catch(Exception e2){
					dao.rollback();
					throw e2;
				}finally{
					dao.closeConnection();
				}
			}
		}catch (Exception e) {
			dao.rollback();
			throw e;
		}finally{
			dao.closeConnection();
		}
	}
	/**
	 * 添加学生 同时添加用户
	 * @param ts
	 * @throws Exception
	 */
	public void addStu(TStudent ts) throws NoSuchClassException,Exception{
		StuDao dao = new StuDao();
		ClaDao cldao = new ClaDao();
		UserDao sdao = new UserDao();
		try {
			dao.beginTransaction();
			cldao.setCon(dao.getCon());
			sdao.setCon(dao.getCon());
			TClass clazz = cldao.finClass(ts.getCno());
			if(clazz == null){
				throw new NoSuchClassException("该班级不存在");
			}
			dao.addStu(ts);
			User user = createUser(ts);
			sdao.addUser(user);
			addStuLog(ts,dao);
			dao.commit();
		}catch(NoSuchClassException e){
			throw e;
	    }catch (Exception e) {
	    	dao.rollback();
			throw e;
		}finally{
			dao.closeConnection();
		}
	}

	/**
	 * 添加学生时，添加日志记录
	 * @param ts
	 * @param dao
	 * @throws Exception
	 */
	private void addStuLog(TStudent ts, StuDao dao) throws Exception {

		LogDao ldao = new LogDao();
		ldao.setCon(dao.getCon());
		TLog log = new TLog();
		log.setOperator("admin");
		log.setPotime(new Date());
	    String info = "添加学生---"+ts.getSname()+"学号："+ts.getSno();
	    log.setInfo(info);
		ldao.addLog(log);
	}
	
	/**
	 * 查找学生
	 * @param sno
	 * @return
	 * @throws Exception
	 */
	public TStudent getStu(String sno) throws Exception{
		StuDao dao = new StuDao();
		TStudent stu = null;
		try {
			stu = dao.getStu(sno);
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		return stu;
	}
	
	/**
	 * 学生选课  根据课程号选课  选一个课程
	 * @param stu
	 * @param kno
	 * @throws NoSuchCourseException
	 * @throws HadTheRecException
	 * @throws Exception
	 */
	public void optCou(TStudent stu, String kno) throws NoSuchCourseException, HadTheRecException, Exception{
		StuDao dao = new StuDao();
		CouDao cdao = new CouDao();
		try {
			dao.beginTransaction();
			cdao.setCon(dao.getCon());
			TCourse cou = cdao.getCou(kno);
			if(cou == null){
				throw new NoSuchCourseException("此课程不存在");
			}
			addTsc(stu, cou, dao);
			dao.commit();
		}catch(NoSuchCourseException e){
			throw e;
		}catch(HadTheRecException e){
			throw e;
		}catch (Exception e) {
			dao.rollback();
			throw e;
		}finally{
			dao.closeConnection();
		}
	}

	/**
	 * 课程存在时，再判断是否已有记录，否，则往课程表添加学生的选课信息
	 * @param stu
	 * @param cou
	 * @param dao
	 * @throws Exception 
	 */
	private void addTsc(TStudent stu, TCourse cou, StuDao dao) throws HadTheRecException,Exception {
		TscDao tdao = new TscDao();
		tdao.setCon(dao.getCon());
		TStuCourse tsc = null;
        tsc=tdao.getCourse(stu.getSno(), cou.getKno());		
		if(tsc == null){
			tsc = new TStuCourse();
			tsc.setKno(cou.getKno());
			tsc.setSno(stu.getSno());
			tdao.addTsc(tsc);
			addTscLog(tsc, dao);
		}else{
			throw new HadTheRecException("该课程已选择");
		}
	}
	
	/**
	 * 添加学生选课日志记录
	 * @param tsc
	 * @param dao
	 * @throws Exception
	 */
	private void addTscLog(TStuCourse tsc, StuDao dao) throws Exception {
		LogDao ldao = new LogDao();
		ldao.setCon(dao.getCon());
		TLog log = new TLog();
		log.setOperator("adimn");
		log.setPotime(LogTime.getTimestamp());
		String info = "添加学生课程表----  "+tsc.getSno()+"++"+tsc.getKno();
		log.setInfo(info);
		ldao.addLog(log);
	}
	
	
}
