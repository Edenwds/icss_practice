package com.my.util;

import java.util.List;

import com.my.contants.TemplateParms;
import com.my.entity.TNews;

public class MainUtil {
	public static void creatMain(List<TNews> hl,List<TNews> nhl) throws Exception{
		//生成主页的地址
		String dpath="D:/MyEclipse/myel/NewsSys/WebRoot/";
		CharStreamIO io = new CharStreamIO();
		//读模板文件
		String tmp = io.readFile("D:/MyEclipse/myel/NewsSys/WebRoot/output/temp/frontemp.html");
		StringBuilder hn1 = new StringBuilder();
		StringBuilder hn2 = new StringBuilder();
		StringBuilder hn3 = new StringBuilder();
		StringBuilder nh = new StringBuilder();
		for(int i = hl.size() - 1,j=1; i >= 0; i--){
			if(i>=15){
//				String s="<a href='output/html/"+hl.get(i).getNid()+".html' title='"+hl.get(i).getTitle()+"' target='_blank'><img src='"+hl.get(i).getCoverurl()+"' width='600' height='400' alt='Slide"+j+++"'></a>";
				hn1.append("<a href='output/html/"+hl.get(i).getNid()+".html' title='"+hl.get(i).getTitle()+"' target='_blank'><img src='"+hl.get(i).getCoverurl()+"' width='600' height='400' alt='Slide"+j+++"'></a>");
			}
			if(i < 15 && i >= 3){
//				String s="<tr><td style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'><a href='output/html/"+hl.get(i).getNid()+".html' title='"+hl.get(i).getTitle()+"' target='_blank'>"+hl.get(i).getTitle()+"</a>";
				hn2.append("<tr><td style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'><a href='output/html/"+hl.get(i).getNid()+".html' title='"+hl.get(i).getTitle()+"' target='_blank'>"+hl.get(i).getTitle()+"</a>");
			}
			if(i < 3 && i >= 0){
//				String s="<tr><td><a href='output/html/"+hl.get(i).getNid()+".html' title='"+hl.get(i).getTitle()+"' target='_blank'><img src='"+hl.get(i).getCoverurl()+"' width='290' height='200'/></a></td></tr><tr><td><a href='output/html/"+hl.get(i).getNid()+".html' title='"+hl.get(i).getTitle()+"' target='_blank'>"+hl.get(i).getTitle()+"</a>";
				hn3.append("<tr><td><a href='output/html/"+hl.get(i).getNid()+".html' title='"+hl.get(i).getTitle()+"' target='_blank'><img src='"+hl.get(i).getCoverurl()+"' width='290' height='200'/></a></td></tr><tr><td><a href='output/html/"+hl.get(i).getNid()+".html' title='"+hl.get(i).getTitle()+"' target='_blank'>"+hl.get(i).getTitle()+"</a>");
			}
		}
		for(int i = 0, j = 1; i < nhl.size();i++,j++){
//			String s="<tr><td width='320px' style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'><a href='output/html/"+nhl.get(i).getNid()+".html' target='_blank'>"+nhl.get(i).getTitle()+"</a></td><td width='80px'></td><td width='300px' style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'>"+nhl.get(i).getPubtime()+"</td></tr>";
			nh.append("<tr><td width='320px' style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'><a href='output/html/"+nhl.get(i).getNid()+".html' target='_blank'>"+nhl.get(i).getTitle()+"</a></td><td width='80px'></td><td width='300px' style='white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'>"+nhl.get(i).getPubtime()+"</td></tr>");
			if(j % 5 == 0){
				nh.append("<tr><td colspan='3'><hr><br></td></tr>");
			}
		}
		
		String ntp = "";
		ntp = tmp.replace(TemplateParms.HOTNEWS1, hn1);
		ntp = ntp.replace(TemplateParms.HOTNEWS2, hn2);
		ntp = ntp.replace(TemplateParms.HOTNEWS3, hn3);
		ntp = ntp.replace(TemplateParms.NOTHOTNEWS, nh);
		io.writeFile(ntp, dpath+"main.html");
	}
}
