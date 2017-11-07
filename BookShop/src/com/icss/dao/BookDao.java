package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.icss.entity.TBook;
import com.icss.entity.TOrderDetail;

public class BookDao extends BaseDao {

	/**
	 * 新书上架
	 * @param book
	 * @throws Exception 
	 */
	public void addBook(TBook book) throws Exception {
		String sql = "insert into tbook values(?,?,?,?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, book.getIsbn());
		ps.setString(2, book.getBname());
		ps.setString(3, book.getAuthor());
		if(book.getPudate() == null){
			ps.setDate(4, null);
		}else{
			ps.setDate(4, new java.sql.Date(book.getPudate().getTime()));
		}
		ps.setString(5, book.getPress());
		ps.setDouble(6, book.getPrice());
		ps.setString(7, book.getDescr());
		ps.setBytes(8, book.getPic());
		ps.setInt(9, 0);
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * 得到购物车中的书
	 * @param keySet
	 * @return
	 * @throws Exception 
	 */
	public List<TBook> getShopcarBooks(Set<String> isbns) throws Exception {
		List<TBook> books = null;
		String isbnsString = null;
		int i = 0;
		for(String isbn : isbns){
			if(i == 0){
				isbnsString = "'" + isbn + "'";
			}else{
				isbnsString = isbnsString + ",'" + isbn +"'";
			}
			i = i + 1;
		}
		String sql = "select isbn,bname,author,price from tbook where isbn in (" + isbnsString + ")";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<TBook>();
		while(rs.next()){
			TBook book = new TBook();
			book.setIsbn(rs.getString("isbn"));
			book.setBname(rs.getString("bname"));
			book.setAuthor(rs.getString("author"));
			book.setPrice(rs.getDouble("price"));
			books.add(book);
		}
		rs.close();
		ps.close();
		return books;
	}
	/**
	 * 获取某本书的详细信息
	 * @param isbn
	 * @return
	 * @throws Exception 
	 */
	public TBook getBookDetail(String isbn) throws Exception{
		TBook book = null;
		String sql = "SELECT bname,author,pubdate,press,price,descr FROM tbook WHERE isbn=?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			book = new TBook();
			book.setIsbn(isbn);
			book.setBname(rs.getString("bname"));
			book.setAuthor(rs.getString("author"));
			book.setPudate(rs.getDate("pubdate"));
			book.setPress(rs.getString("press"));
			book.setPrice(rs.getDouble("price"));
			book.setDescr(rs.getString("descr"));
		}
		
		return book;
	}
	/**
	 * 获取图书封面图片
	 * @return
	 * @throws Exception 
	 */
	public byte[] getBookPic(String isbn) throws Exception{
		byte[] pic = null;
		String sql = "Select pic From Tbook Where isbn=? ";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			pic = rs.getBytes("pic");
		}
		rs.close();
		ps.close();
		
		return pic;
	}
	/**
	 * 返回所有图书信息
	 * @return
	 * @throws Exception 
	 */
	public List<TBook> getAllBooks() throws Exception {
		List<TBook> books = null;
		String sql = "SELECT isbn,bname,author,pubdate,press,price,descr FROM tbook";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<TBook>();
		while(rs.next()){
			TBook book = new TBook();
			book.setIsbn(rs.getString("isbn"));
			book.setBname(rs.getString("bname"));
			book.setAuthor(rs.getString("author"));
			book.setPudate(rs.getDate("pubdate"));
			book.setPress(rs.getString("press"));
			book.setPrice(rs.getDouble("price"));
			book.setDescr(rs.getString("descr"));
			books.add(book);
		}
		return books;
	}
	/**
	 * 生成订单，返回订单编号
	 * @param uname
	 * @param allmoney
	 * @return
	 * @throws Exception 
	 */
	public String createNewOrder(String uname, double allmoney) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String oid = "oid-"+format.format(new Date());
		String sql = "insert into torder values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, oid);
		ps.setString(2, uname);
		ps.setDouble(3, allmoney);
		ps.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
		ps.executeUpdate();
		ps.close();
		return oid;
	}
	/**
	 * 添加订单明细
	 * @param detail
	 * @throws Exception 
	 */
	public void addOrderDetail(TOrderDetail detail) throws Exception {
		String sql = "insert into torderdetail values((select nvl(max(aid),0)+1 from torderdetail),?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, detail.getOid());
		ps.setString(2, detail.getIsbn());
		ps.setInt(3, detail.getBuycount());
		ps.setDouble(4, detail.getDealprice());
		ps.executeUpdate();
		ps.close();
		cutBookCount(detail.getIsbn(),detail.getBuycount());   //扣库存
	}
	
	/**
	 * 扣库存，减少库存数量
	 * @param isbn
	 * @param buycount
	 * @throws Exception 
	 */
	private void cutBookCount(String isbn, int buycount) throws Exception {
		String sql = "update tbook set bkcount = bkcount-? where isbn=?";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setInt(1, buycount);
		ps.setString(2, isbn);
		ps.executeUpdate();
		ps.close();
		
	}
}
