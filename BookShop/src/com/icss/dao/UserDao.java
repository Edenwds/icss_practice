package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.dto.Buyinfo;
import com.icss.dto.TurnPage;
import com.icss.entity.BUser;

public class UserDao extends BaseDao{

	public BUser login(String uname, String pwd) throws Exception {
		BUser user = null;
		String sql = "select * from buser where uname=? and pwd=?";
//		this.openConnection(DB_URL);  //使用连接池打开连接
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			user = new BUser();
			user.setUname(uname);
			user.setPwd(pwd);
			user.setRole(rs.getInt("role"));
			user.setAccount(rs.getDouble("account"));
		}
		rs.close();
		ps.close();
		
		return user;
	}

	/**
	 * 进行账户扣款
	 * @param uname
	 * @param allmoney
	 * @throws Exception 
	 */
	public void cutAccont(String uname, double allmoney) throws Exception {
		String sql = "update buser set account =account-? where uname =?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setDouble(1, allmoney);
		ps.setString(2, uname);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 查找所有用户的购买信息
	 * @param uname
	 * @param bDate
	 * @param eDate
	 * @param tp 
	 * @return
	 * @throws Exception 
	 */
	public List<Buyinfo> findUserBuyinfo(String uname, Date bDate, Date eDate, TurnPage tp) throws Exception {
		List<Buyinfo> infoList = null;
		String sql = "SELECT  o.uname,  o.oid,  o.allmoney,  o.paytime,  d.aid,  d.buycount, "
							+ " d.dealprice,  b.isbn,  b.bname,  b.author,  b.pubdate,  b.press,  b.bkcount"
                             +" FROM torder o,torderdetail d,tbook b WHERE o.oid=d.oid AND d.isbn = b.isbn";
		if(uname != null && !uname.trim().equals("")){
			sql = sql + " and o.uname like '%"+uname+"%'";
		}
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		if(bDate != null){
			String bdate = format.format(bDate);
			sql = sql + " and o.paytime >=to_date('"+bdate+"','mm/dd/yyyy')";
		}
		if(eDate != null){
			String edate = format.format(eDate);
			sql = sql + " and o.paytime <to_date('"+edate+"','mm/dd/yyyy')";
		}
	    int allRows = this.getSqlCount(sql);
	    int allPages = (allRows - 1) / tp.rows + 1;
	    if(tp.pageno > allPages){
	    	tp.pageno = allPages;
	    }
	    tp.allPages = allPages;
	    tp.allRows = allRows;
	    int start = (tp.pageno - 1) * tp.rows + 1;
	    int end = start + tp.rows - 1;
	    String newSql = this.getTurnPageSql(sql,start,end);
	    this.openConnection();
	    PreparedStatement ps = this.con.prepareStatement(newSql);
	    ResultSet rs = ps.executeQuery();
	    infoList = new ArrayList<Buyinfo>();
	    while(rs.next()){
	    	Buyinfo info = new Buyinfo();
	    	info.setAid(rs.getInt("aid"));
			info.setAllmoney(rs.getDouble("allmoney"));
			info.setAuthor(rs.getString("author"));
			info.setBkcount(rs.getInt("bkcount"));
			info.setBname(rs.getString("bname"));
			info.setDealprice(rs.getDouble("dealprice"));
			info.setIsbn(rs.getString("isbn"));
			info.setOid(rs.getString("oid"));
			info.setPaytime(rs.getTimestamp("paytime"));
			info.setPress(rs.getString("press"));
			info.setUname(rs.getString("uname"));
			infoList.add(info);
	    }
	    rs.close();
	    ps.close();
		return infoList;
	}

}
