package com.icss.control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopcarRemoveSvl
 */
public class ShopcarRemoveSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarRemoveSvl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		if(isbn != null){
			Map<String,Integer> shopcar = (Map<String, Integer>) request.getSession().getAttribute("shopcar");
			
			shopcar.remove(isbn);
			request.getRequestDispatcher("/user/ShopcarSvl").forward(request, response);
		}else{
			request.setAttribute("errMsg", "丢失参数isbn");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}


}
