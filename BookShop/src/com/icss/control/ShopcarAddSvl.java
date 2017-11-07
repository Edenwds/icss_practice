package com.icss.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ShopcarAddSvl
 */
public class ShopcarAddSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarAddSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//   使用过滤器进行权限校验
//		Object user = request.getSession().getAttribute("user");
//		if(user != null){
			String isbn = request.getParameter("isbn");
			if(isbn == null){
				request.setAttribute("errMsg", "丢失参数isbn");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}else{
				Object obj = request.getSession().getAttribute("shopcar");
				Map<String,Integer> shopcar = null;
				if(obj == null){
					shopcar = new HashMap<String,Integer>();
					request.getSession().setAttribute("shopcar", shopcar);
				}else{
					shopcar = (Map<String, Integer>) obj;
				}
				shopcar.put(isbn, 1);
				request.getRequestDispatcher("/user/ShopcarSvl").forward(request, response);
			}
//		}else{
//			request.setAttribute("msg", "添加商品到购物车，请先登录");
//			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
//		}
	}

}
