package com.icss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.icss.contants.IRole;
import com.icss.entity.BUser;

public class AdminAuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Object user = req.getSession().getAttribute("user");
		if(user == null){
			req.setAttribute("msg", "无权访问，请先登录");
			req.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		}else{
			BUser u = (BUser) user;
			if(u.getRole() == IRole.ADMIN){
				chain.doFilter(request, response);
			}else{
				req.setAttribute("msg", "无权访问，请先登录");
				req.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
