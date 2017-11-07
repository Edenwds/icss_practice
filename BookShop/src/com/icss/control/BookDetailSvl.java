package com.icss.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;
import com.icss.util.Log;

/**
 * Servlet implementation class BookDetailSvl
 */
public class BookDetailSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		if(isbn != null){
			BookBiz biz = new BookBiz();
			try {
				TBook book = biz.getBookDetail(isbn);
				request.setAttribute("book", book);
				request.getRequestDispatcher("/WEB-INF/main/BookDetail.jsp").forward(request, response);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				request.setAttribute("errMsg", "网络异常，请和管理员联系");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("errMsg", "参数isbn为空");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
