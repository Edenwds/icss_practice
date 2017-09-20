package com.icss.entity;

public class User {

	private String uname;
	private String pwd;
	private int role;                //1 管理员 2 普通用户 3 VIP用户
	private String tel;
	
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [uname=" + uname + ", pwd=" + pwd + ", role=" + role
				+ ", tel=" + tel + "]";
	}
	
}
