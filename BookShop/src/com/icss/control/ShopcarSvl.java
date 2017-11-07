package com.icss.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;
import com.icss.util.Log;

/**
 * Servlet implementation class ShopcarSvl
 */
public class ShopcarSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 当用户浏览购物车时，先判断是否有购物车，若没有，则为第一次浏览，为用户添加购物车到session
     * 通过购物车的keySet集合(为书的编号)得到购物车中的书
     */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object obj = request.getSession().getAttribute("shopcar");
		Map<String,Integer> shopcar = null;
		if(obj == null){
			shopcar = new HashMap<String,Integer>();
			request.getSession().setAttribute("shopcar", shopcar);
		}else{
			shopcar = (Map<String, Integer>) obj;
		}
		BookBiz biz = new BookBiz();
		try {
			List<TBook> books = biz.getShopcarBooks(shopcar.keySet());
			request.setAttribute("shopBooks", books);
			request.getRequestDispatcher("/WEB-INF/main/shopCar.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
			request.setAttribute("errMsg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
