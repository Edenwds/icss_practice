package com.icss.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;

/**
 * Servlet implementation class CheckoutSvl
 */
public class CheckoutSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Integer> shopcar = (Map<String, Integer>) request.getSession().getAttribute("shopcar");
		BookBiz biz = new BookBiz();
		try {
			List<TBook> books = biz.getShopcarBooks(shopcar.keySet());
			double allmoney = 0;
			for(TBook book : books){
				String bcount = request.getParameter(book.getIsbn());
				int buycount = Integer.parseInt(bcount);
				book.setBuycount(buycount);
				shopcar.put(book.getIsbn(), buycount);
				allmoney = allmoney + buycount * book.getPrice();
			}
			request.setAttribute("allmoney", allmoney);
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/checkout.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
