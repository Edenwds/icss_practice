package com.my.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.my.dto.TurnPage;
import com.my.entity.TCategory;
import com.my.entity.TNews;
import com.my.util.HotNews;

public class NewsDao extends BaseDao {

	/**
	 * 得到热点之外的其他新闻
	 * @return
	 * @throws Exception 
	 */
	public List<TNews> getNoHotNews(List<String> hotids) throws Exception {
		List<TNews> list = null;
		String sql="select nid,title,pubtime from tnews where nid not in(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) and rownum<21";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		for(int i = 0; i < hotids.size(); i++){
			ps.setString(i + 1, hotids.get(i));
		}
		ResultSet rs = ps.executeQuery();
		list = new ArrayList<TNews>();
		while(rs.next()){
			TNews news = new TNews();
			news.setNid(rs.getString("nid"));
			news.setTitle(rs.getString("title"));
			news.setPubtime(rs.getTimestamp("pubtime"));
			list.add(news);
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
		List<TNews> list = null;
		String sql = "select nid,title,coverurl from tnews where nid in (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		for(int i = 0; i < hotids.size(); i++){
			ps.setString(i + 1, hotids.get(i));
		}
		ResultSet rs = ps.executeQuery();
		list = new ArrayList<TNews>();
		while(rs.next()){
			TNews news = new TNews();
			news.setNid(rs.getString("nid"));
			news.setTitle(rs.getString("title"));
			news.setCoverurl(rs.getString("coverurl"));
			list.add(news);
		}
		return list;
	}

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
		String sql = "select nid,title,pubtime,source from tnews where cid='"+ntype+"'";
		if(keyword != null && !keyword.trim().equals("")){
			sql = sql +" and title like '%"+keyword+"%'";
		}
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		if(bDate != null){
			String bdate = format.format(bDate);
			sql = sql +" and pubtime >=to_date('"+bdate+"','mm/dd/yyyy')";
		}
		if(eDate != null){
			String edate = format.format(eDate);
			sql = sql +" and pubtime <to_date('"+edate+"','mm/dd/yyyy')";
		}
		int allRows = this.getSqlCount(sql);
		int allPages = (allRows - 1)/tp.rows + 1;
		if(tp.pageno > allPages){
			tp.pageno = allPages;
		}
		tp.allPages = allPages;
		tp.allRows = allRows;
		int start = (tp.pageno - 1) * tp.rows + 1;
		int end = start + tp.rows - 1;
		String newSql = this.getTurnPageSql(sql, start, end);
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(newSql);
		ResultSet rs = ps.executeQuery();
		bnewslist = new ArrayList<TNews>();
		while(rs.next()){
			TNews news = new TNews();
			news.setNid(rs.getString("nid"));
			news.setTitle(rs.getString("title"));
			news.setPubtime(rs.getTimestamp("pubtime"));
			news.setSource(rs.getString("source"));
			bnewslist.add(news);
		}
		
		//读取热点新闻的配置文件  判断查找的新闻是否有热点新闻设置标志
		HotNews hn = HotNews.instance();
		hn.reload();
		List<String> hotlist = hn.getHotNids();
		HashSet<String> hotset = new HashSet<String>(hotlist);
		for(TNews news : bnewslist){
			String nid = news.getNid();
			if(hotset.contains(nid)){
				news.setIshot("是");
			}else{
				news.setIshot("否");
			}
		}
		return bnewslist;
	}
	/**
	 * 获取所有的新闻分类
	 * @return
	 * @throws Exception 
	 */
	public List<TCategory> getAllcategory() throws Exception {
		List<TCategory> list = null;
		String sql="select cid,cname from tcategory";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		list = new ArrayList<TCategory>();
		while(rs.next()){
			TCategory cg = new TCategory();
			cg.setCid(rs.getString("cid"));
			cg.setCname(rs.getString("cname"));
			list.add(cg);
		}
		return list;		
	}
	/**
	 * 主页显示全部新闻内容
	 * @return
	 * @throws Exception 
	 */
	public List<TNews> getAllNews(TurnPage tp) throws Exception {
		List<TNews> newslist = null;
		String sql = "select nid,title,pubtime from tnews";
		int allRows = this.getSqlCount(sql);
		int allPages = (allRows - 1)/tp.rows + 1;
		if(tp.pageno > allPages){
			tp.pageno = allPages;
		}
		tp.allPages = allPages;
		tp.allRows = allRows;
		int start = (tp.pageno - 1)*tp.rows + 1;
		int end = start + tp.rows - 1;
		String newSql = this.getTurnPageSql(sql, start, end);
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(newSql);
		ResultSet rs = ps.executeQuery();
		newslist = new ArrayList<TNews>();
		while(rs.next()){
			TNews news = new TNews();
			news.setNid(rs.getString("nid"));
			news.setTitle(rs.getString("title"));
			news.setPubtime(rs.getTimestamp("pubtime"));
			newslist.add(news);
		}
		rs.close();
		ps.close();
		return newslist;
	}
	
	/**
	 * 存储新闻内容
	 * @param news
	 * @throws Exception 
	 */
	public void saveNews(TNews news) throws Exception {
		String sql = "insert into tnews values(?,?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, news.getNid());
		ps.setString(2, news.getCid());
		ps.setString(3, news.getTitle());
		ps.setTimestamp(4, new java.sql.Timestamp(news.getPubtime().getTime()));
		ps.setString(5, news.getSource());
		ps.setString(6, news.getInfo());
		ps.setString(7, news.getCoverurl());
		ps.executeUpdate();
		ps.close();
		
	}

}
