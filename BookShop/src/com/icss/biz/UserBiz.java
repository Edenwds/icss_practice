package com.icss.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.icss.dao.BookDao;
import com.icss.dao.UserDao;
import com.icss.dto.TurnPage;
import com.icss.dto.Buyinfo;
import com.icss.entity.BUser;
import com.icss.entity.TBook;
import com.icss.entity.TOrderDetail;
import com.icss.util.Log;

public class UserBiz {

	public BUser login(String uname, String pwd) throws Exception{
		BUser user = null;
		if(uname != null && pwd != null && !uname.equals("") && !pwd.equals("")){
			UserDao dao = new UserDao();
			try{
				user = dao.login(uname, pwd);
			}catch(Exception e){
				 Log.logger.error(e.getMessage());
				 throw e;
			}finally{
				dao.closeConnection();
			}
		}else{
			throw new Exception("入参错误，请检查");
		}
		return user;
	}

	/**
	 * 用户对购物车确认付款
	 * @param uname
	 * @param allmoney
	 * @param shopcar
	 * @throws Exception 
	 */
	public void buyBooks(String uname, double allmoney,
			Map<String, Integer> shopcar) throws Exception {
		if(uname != null && shopcar != null){
			UserDao udao = new UserDao();
			try {
				udao.beginTransaction();
				//1.账户扣款
			    udao.cutAccont(uname,allmoney);
			    //2.生成订单
			    BookDao  bdao = new BookDao();
			    bdao.setCon(udao.getCon());
			    String orderid = bdao.createNewOrder(uname,allmoney);
			    //3.生成订单明细  4.减库存
			    List<TBook> books = bdao.getShopcarBooks(shopcar.keySet());
			    for(TBook book : books){
			    	TOrderDetail detail = new TOrderDetail();
			    	detail.setOid(orderid);
			    	detail.setIsbn(book.getIsbn());
			    	detail.setBuycount(shopcar.get(book.getIsbn()));
			    	detail.setDealprice(book.getPrice());
			    	bdao.addOrderDetail(detail);
			    }
			    udao.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				udao.closeConnection();
			}
		}else{
			throw new Exception("入参错误");
		}
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
		UserDao dao = new UserDao();
		try {
			infoList = dao.findUserBuyinfo(uname, bDate, eDate, tp);
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
			throw e;
		}finally{
			dao.closeConnection();
		}
		return infoList;
	}

	
}
