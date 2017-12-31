package com.my.util;

import java.text.SimpleDateFormat;

import com.my.contants.TemplateParms;
import com.my.entity.TNews;

public class NewsUtil {
	public static void creatNews(TNews news) throws Exception{
		//生成页面的地址
		String dpath = "D:/MyEclipse/myel/NewsSys/WebRoot/output/html/";
		CharStreamIO io = new CharStreamIO();
		//读模板文件
		String tmp = io.readFile("D:/MyEclipse/myel/NewsSys/WebRoot/output/temp/newstemp.html");
		String ntp = "";
		ntp = tmp.replace(TemplateParms.TITLE, news.getTitle());
		ntp = ntp.replace(TemplateParms.SOURCE, news.getSource());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pubTime = format.format(news.getPubtime());
		ntp = ntp.replace(TemplateParms.PUB_TIME, pubTime);
		ntp = ntp.replace(TemplateParms.CONTENT, news.getInfo());
		io.writeFile(ntp, dpath+news.getNid()+".html");
	}
}
