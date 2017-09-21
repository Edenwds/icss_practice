package com.icss.news.ui;

import com.icss.news.biz.NewsBiz;

public class NewsTest {

	public static void main(String[] args) {
		NewsBiz biz = new NewsBiz();
		try {
			biz.createAllNews();
			System.out.println("页面创建完成");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
