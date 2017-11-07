package com.icss.biz;

import java.util.List;
import java.util.Set;

import com.icss.dao.BookDao;
import com.icss.dao.UserDao;
import com.icss.entity.TBook;
import com.icss.util.Log;

public class BookBiz {

	/**
	 * 获取某本书的详细信息
	 * @param isbn
	 * @return
	 * @throws Exception 
	 */
	public TBook getBookDetail(String isbn) throws Exception{
		TBook book = null;
		if(isbn != null){
			BookDao dao = new BookDao();
			book = dao.getBookDetail(isbn);
		}else{
			throw new Exception("参数isbn不能为空");
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
		if(isbn != null){
			BookDao dao = new BookDao();
			try {
				pic = dao.getBookPic(isbn);
			} catch (Exception e) {
				throw e;
			}finally{
				dao.closeConnection();
			}
		}else{
			throw new Exception("参数isbn不能为空");
		}
		
		return pic;
	}
	/**
	 * 返回所有图书信息
	 * @return
	 * @throws Exception 
	 */
	public List<TBook> getAllBooks() throws Exception {
		List<TBook> books = null;
		BookDao dao = new BookDao();
		try {
			books = dao.getAllBooks();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		return books;
	}
	/**
	 * 得到购物车中的书
	 * @param keySet
	 * @return
	 * @throws Exception 
	 */
	public List<TBook> getShopcarBooks(Set<String> isbns) throws Exception {
		List<TBook> books = null;
		if(isbns != null && isbns.size() > 0){
			BookDao dao = new BookDao();
			try {
				books = dao.getShopcarBooks(isbns);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				throw e;
			}finally{
				dao.closeConnection();
			}
		}
		return books;
	}
	/**
	 * 新书上架
	 * @param book
	 * @throws Exception 
	 */
	public void addBook(TBook book) throws Exception {
		BookDao dao = new BookDao();
		try {
			dao.addBook(book);
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
			throw e;
		}finally{
			dao.closeConnection();
		}
	}
}
