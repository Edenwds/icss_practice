package com.icss.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.OnlineUser;
import com.icss.biz.UserBiz;
import com.icss.entity.BUser;

/**
 * Servlet implementation class LoginSvl
 */
public class LoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 在实现自动登录时，必须在index.jsp页面进行登录校验，如果在servlet进行，则无法退出
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserBiz biz = new UserBiz();
		try {
			BUser user = biz.login(uname, pwd);
			if(user != null){
				request.getSession().setAttribute("user", user);
//				//使用cookie实现自动登录
//				Cookie cookie = new Cookie("user",uname+","+pwd);
//				cookie.setMaxAge(60*60*24);//设置生命周期
//				response.addCookie(cookie);
				//统计在线用户数
				OnlineUser.addUser(request.getSession().getId(), user);
				System.out.println(user.getUname() + "登录成功！当前在线人数：" + OnlineUser.getUsers().size());
				request.getRequestDispatcher("/MainSvl").forward(request, response);
			}else{
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
