package com.icss.entity;

import java.util.Date;


public class TStudent {

	private String sname;
	private String sno;
	private String cno;
	private String sex;
	private String address;
	private String tel;
	private Date birthday;
	private double height;
	private int state;
//	private TClass tclass; // 班级和学生是一对多的关系 
//	private User user;   // 用户和学生是一对一的关系

	
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public TClass getTclass() {
//		return tclass;
//	}
//	public void setTclass(TClass tclass) {
//		this.tclass = tclass;
//	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "TStudent [sname=" + sname + ", sno=" + sno + ", cno=" + cno
				+ ", sex=" + sex + ", address=" + address + ", tel=" + tel
				+ ", birthday=" + birthday + ", height=" + height + ", state="
				+ state;
	}
}
