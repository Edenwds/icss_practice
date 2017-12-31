package com.my.entity;

import java.util.Date;

public class TNews {
	private String  nid;
	private String cid;   //所属类别
	private String title;
	private Date pubtime;
	private String source;
	private String info; //新闻内容
	private String coverurl; //封面图片的地址
	private String ishot; // 是或否
	
	
	public String getIshot() {
		return ishot;
	}
	public void setIshot(String ishot) {
		this.ishot = ishot;
	}
	public String getCoverurl() {
		return coverurl;
	}
	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
} 
