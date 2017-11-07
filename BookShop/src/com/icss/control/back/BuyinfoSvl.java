package com.icss.control.back;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.dto.TurnPage;
import com.icss.dto.Buyinfo;
import com.icss.util.Log;

/**
 * Servlet implementation class BuyinfoSvl
 */
public class BuyinfoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyinfoSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		Date bDate = null , eDate = null;
		UserBiz biz = new UserBiz();
			try {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				if(beginDate != null && !beginDate.trim().equals("")){
					bDate = format.parse(beginDate);
				}
				if(endDate != null && !endDate.trim().endsWith("")){
					eDate = format.parse(endDate);
				}
				String page = request.getParameter("page"); // 获取页面请求的页号
				int initpage = 1;
				if(page != null){
					initpage = Integer.parseInt(page);
					if(initpage < 1)
						initpage = 1;
				}
				TurnPage tp = new TurnPage();
				tp.pageno = initpage;
				List<Buyinfo> infoList = biz.findUserBuyinfo(uname,bDate,eDate,tp);
				request.setAttribute("infoList", infoList);
				request.setAttribute("uname", uname);
				request.setAttribute("beginDate", beginDate);
				request.setAttribute("endDate", endDate);
				request.setAttribute("currentPage", tp.pageno);
				request.setAttribute("allRows", tp.allRows);
				request.setAttribute("allPages", tp.allPages);
				request.getRequestDispatcher("/WEB-INF/back/BuyinfoList.jsp").forward(request, response);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				request.setAttribute("errMsg", "网络异常，请和管理员联系");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
	}

}
