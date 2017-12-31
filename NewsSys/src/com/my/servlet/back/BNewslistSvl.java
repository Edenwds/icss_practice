package com.my.servlet.back;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.biz.NewsBiz;
import com.my.dto.TurnPage;
import com.my.entity.TCategory;
import com.my.entity.TNews;
import com.my.util.GetAllCategory;
import com.my.util.Log;

/**
 * Servlet implementation class BNewslistSvl
 */
public class BNewslistSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BNewslistSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String ntype = request.getParameter("ntype");
		String keyword = request.getParameter("keyword");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		Date bDate = null, eDate = null;
		NewsBiz biz = new NewsBiz();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			if(beginDate != null && !beginDate.trim().equals("")){
				bDate = format.parse(beginDate);
			}
			if(endDate != null && !endDate.trim().equals("")){
				eDate = format.parse(endDate);
			}
			String page = request.getParameter("page");
			int initpage = 1;
			if(page != null){
				initpage = Integer.parseInt(page);
				if(initpage < 1)
					initpage = 1;
			}
			TurnPage tp = new TurnPage();
			tp.pageno = initpage;
			List<TNews> newslist = biz.getBackAllNews(tp, ntype, keyword, bDate, eDate);
			request.setAttribute("newslist", newslist);
			request.setAttribute("ntype", ntype);
			request.setAttribute("keyword", keyword);
			request.setAttribute("beginDate", beginDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("pageno", tp.pageno);
			request.setAttribute("allRows", tp.allRows);
			request.setAttribute("allPages", tp.allPages);
			
			//每次翻页都要得到所有的类型
			List<TCategory> allcg = null;
			allcg = GetAllCategory.getall();
			request.setAttribute("allcg", allcg);
			request.getRequestDispatcher("/WEB-INF/jsp/back/bnewslist.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}
		
	}

}
