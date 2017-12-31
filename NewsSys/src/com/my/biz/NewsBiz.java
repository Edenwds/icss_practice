package com.my.biz;

import java.util.Date;
import java.util.List;

import com.my.dao.NewsDao;
import com.my.dto.TurnPage;
import com.my.entity.TNews;
import com.my.entity.TCategory;

public class NewsBiz {

	/**
	 * 后台多条件查询新闻列表
	 * @param tp
	 * @param ntype
	 * @param keyword
	 * @param bDate
	 * @param eDate
	 * @return
	 * @throws Exception 
	 */
	public List<TNews> getBackAllNews(TurnPage tp, String ntype, String keyword,
			Date bDate, Date eDate) throws Exception {
	    List<TNews> bnewslist = null;
		NewsDao dao = new NewsDao();
		try {
			bnewslist = dao.getBackAllNews(tp, ntype, keyword, bDate, eDate);
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		return bnewslist;
	}
	/**
	 * 主页显示全部新闻内容 进行分页展示
	 * @return
	 */
	public List<TNews> getAllNews(TurnPage tp) {
		List<TNews> newslist = null;
		NewsDao dao = new NewsDao();
		try{
			newslist = dao.getAllNews(tp);
		}catch(Exception e){
			
		}finally{
			dao.closeConnection();
		}
		return newslist;
	}

	/**
	 * 存储新闻
	 * @param news
	 * @throws Exception 
	 */
	public void saveNews(TNews news) throws Exception {
		NewsDao dao = new NewsDao();
		dao.beginTransaction();
		try {
			dao.saveNews(news);
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
			throw e;
		}finally{
			dao.closeConnection();
		}
	}

	/**
	 * 获取所有的新闻分类
	 * @return
	 * @throws Exception 
	 */
	public List<TCategory> getAllcategory() throws Exception {
		List<TCategory> list = null;
		NewsDao dao = new NewsDao();
		try {
			list = dao.getAllcategory();
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		return list;
	}
	
	/**
	 * 根据热点id 得到所有所有热点新闻
	 * @param hotids
	 * @return
	 * @throws Exception 
	 */
	public List<TNews> getHotNews(List<String> hotids) throws Exception {
		List<TNews> list =null;
		if(hotids != null && hotids.size() > 0){
			NewsDao dao = new NewsDao();
			try {
				list = dao.getHotNews(hotids);
			} catch (Exception e) {
				throw e;
			}finally{
				dao.closeConnection();
			}
		}
		return list;
	}
	
	/**
	 * 得到热点之外的其他新闻
	 * @param hotids 
	 * @return
	 * @throws Exception 
	 */
	public List<TNews> getNoHotNews(List<String> hotids) throws Exception {
		List<TNews> list = null;
		if(hotids != null && hotids.size()>0){
			NewsDao dao = new NewsDao();
			try {
				list = dao.getNoHotNews(hotids);
			} catch (Exception e) {
				throw e;
			}finally{
				dao.closeConnection();
			}
		}else{
			throw new Exception("hotids为空，请检查！");
		}
		return list;
	}

	
}
