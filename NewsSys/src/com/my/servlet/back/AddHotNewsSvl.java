package com.my.servlet.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.biz.NewsBiz;
import com.my.entity.TNews;
import com.my.util.HotNews;
import com.my.util.HotNewsIn;
import com.my.util.Log;
import com.my.util.MainUtil;

/**
 * Servlet implementation class AddHotNewsSvl
 */
public class AddHotNewsSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddHotNewsSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[]  checkID = request.getParameterValues("checkID");
		try {
			HotNewsIn.addHot(checkID);
			HotNews hn = HotNews.instance();
			hn.reload();
			List<String> hotids = hn.getHotNids();
			NewsBiz biz = new NewsBiz();
			List<TNews> hotnews = biz.getHotNews(hotids);
			List<TNews> nothotnews= biz.getNoHotNews(hotids);
			MainUtil.creatMain(hotnews, nothotnews);
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("设置成功");
	}

}
