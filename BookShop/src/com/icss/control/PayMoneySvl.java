package com.icss.control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.entity.BUser;
import com.icss.util.Log;

/**
 * Servlet implementation class PayMoneySvl
 */
public class PayMoneySvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayMoneySvl() {
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
		String money = request.getParameter("allmoney");
		if(money != null){
			double allmoney = Double.parseDouble(money);
			BUser user = (BUser) request.getSession().getAttribute("user");
			Map<String,Integer> shopcar = (Map<String, Integer>) request.getSession().getAttribute("shopcar");
			UserBiz biz = new UserBiz();
			try {
				if(user.getAccount() >= allmoney){
					biz.buyBooks(user.getUname(),allmoney,shopcar);
					user.setAccount(user.getAccount() - allmoney);
					request.setAttribute("allmoney", allmoney);
					request.getRequestDispatcher("/WEB-INF/main/payMoneyOK.jsp").forward(request, response);
				}else{
					request.setAttribute("errMsg", "账户余额不足，请先充值");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				request.setAttribute("errMsg", "网络异常，请和管理员联系");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("errMsg", "参数错误");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}


