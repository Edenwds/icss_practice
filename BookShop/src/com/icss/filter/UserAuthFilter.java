package com.icss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UserAuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
	     HttpServletRequest request = (HttpServletRequest) arg0;
	     Object user = request.getSession().getAttribute("user");
	     if(user == null){
	    	 request.setAttribute("msg", "无权访问，请先登录");
	    	 request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, arg1);
	     }else{
	    	 chain.doFilter(arg0, arg1);
	     }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
		
}
