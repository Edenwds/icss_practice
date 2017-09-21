package com.icss.news.biz;

import java.util.List;

import com.icss.news.contants.TemplateParms;
import com.icss.news.dao.NewsDao;
import com.icss.news.entity.News;
import com.icss.util.CharStreamIO;

public class NewsBiz {

	public void createAllNews() throws Exception{
		NewsDao dao = new NewsDao();
		List<News> list = dao.getAllNews();
		// 页面生成的位置
		String dpath = "F:/实习 课设/中软/9-11/News/newspages";
		//读取模板文件
		CharStreamIO io = new CharStreamIO();
		String tmp = io.readFile("F:/实习 课设/中软/9-11/News/news.tmp");
		for(int i = 0; i < list.size(); i++){
			News news = list.get(i);
			String ntp = "";
			ntp = tmp.replace(TemplateParms.TITLE, news.getTitle());
			ntp = ntp.replace(TemplateParms.CATEGORY, news.getCategory());
			ntp = ntp.replace(TemplateParms.PUB_TIME, news.getPubTime());
			ntp = ntp.replace(TemplateParms.CONTENT, news.getContent());
			
			io.writeFile(ntp, dpath+"/news-"+i+".html");
		}
	}
}
