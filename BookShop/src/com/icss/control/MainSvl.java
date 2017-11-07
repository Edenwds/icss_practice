package com.icss.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;
import com.icss.util.Log;

/**
 * Servlet implementation class MainSvl
 */
public class MainSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookBiz biz = new BookBiz();
		try {
			List<TBook> books = biz.getAllBooks();
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/main.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
			request.setAttribute("errMsg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	
}
