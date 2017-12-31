package com.my.servlet.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.biz.NewsBiz;
import com.my.entity.TCategory;
import com.my.util.GetAllCategory;
import com.my.util.Log;

/**
 * Servlet implementation class ToNewsListSvl
 */
public class ToNewsListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToNewsListSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TCategory> allcg = null;
		allcg = GetAllCategory.getall();
		request.setAttribute("allcg", allcg);
		request.getRequestDispatcher("/WEB-INF/jsp/back/bnewslist.jsp").forward(request, response);
	}

}
