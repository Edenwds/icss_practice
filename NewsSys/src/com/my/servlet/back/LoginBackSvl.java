package com.my.servlet.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.biz.AdminBiz;
import com.my.entity.TAdmin;
import com.my.util.Log;

/**
 * Servlet implementation class LoginBackSvl
 */
public class LoginBackSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginBackSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aname = request.getParameter("aname");
		String apwd = request.getParameter("apwd");
		AdminBiz biz = new AdminBiz();
		TAdmin admin = null;
		try {
			admin = biz.login(aname,apwd);
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}
		
		if(admin != null){
			request.getSession().setAttribute("admin", admin);
			request.getRequestDispatcher("/back/ToNewsListSvl").forward(request, response);
		}else{
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/loginback.jsp").forward(request, response);
		}
		
	}

}
