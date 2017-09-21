package com.icss.news.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.news.entity.News;
import com.icss.util.CharStreamIO;

public class NewsDao {

    /**
     * 从数据库读取信息   这里从给定的文件中读取	
     * @return
     * @throws NoSuchFileException 
     */
	public List<News> getAllNews(){
		CharStreamIO io = new CharStreamIO();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<News> list = new ArrayList<News>();
		
		News n1 = new News();
		n1.setPubTime(sd.format(new Date()));
		String c1 = io.readFile("F:/实习 课设/中软/9-11/NewsInfo/news3.txt");
		n1.setContent(c1);
		n1.setCategory("社会");
		n1.setTitle("普通新闻");
		
		News n2 = new News();
		n2.setCategory("sport");
		n2.setTitle("中国足球");
		n2.setPubTime(sd.format(new Date()));
		String c2 = io.readFile("F:/实习 课设/中软/9-11/NewsInfo/new1.txt");
		n2.setContent(c2);
		
		list.add(n1);
		list.add(n2);
		return list;
	}
}
