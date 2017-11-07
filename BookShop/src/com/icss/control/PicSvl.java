package com.icss.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.util.Log;

/**
 * Servlet implementation class PicSvl
 */
public class PicSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		BookBiz biz = new BookBiz();
		if(isbn != null){
			try {
				byte[] pic = biz.getBookPic(isbn);
				if(pic != null){
					ServletOutputStream out = response.getOutputStream();
					out.write(pic);
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				request.setAttribute("errMsg", "网络异常，请和管理员联系");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("errMsg", "isbn参数丢失");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
