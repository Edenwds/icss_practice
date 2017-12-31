package com.my.util;

import java.util.List;

import com.my.biz.NewsBiz;
import com.my.entity.TCategory;

public class GetAllCategory {
	public static List<TCategory> getall(){
		List<TCategory> allcg = null;
		NewsBiz biz = new NewsBiz();
	    try {
			allcg = biz.getAllcategory();
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}
	    return allcg;
	}
}
