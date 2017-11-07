package com.icss.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharacterEncodingFilter implements Filter{

	protected String encoding = null;
	protected FilterConfig filterConfig = null;
	protected boolean ignore = true;  //是否忽视指定的字符集 默认为true
	@Override
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	void encoding(HttpServletRequest request){
		Enumeration<?> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String[] values = request.getParameterValues(names.nextElement().toString());
			for(int i = 0; i < values.length; i++){
				try {
					values[i] = new String(values[i].getBytes("iso-8859-1"),encoding);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(ignore || (request.getCharacterEncoding() == null)){
			String encoding = selectEncoding(request);
			if(encoding != null){
				request.setCharacterEncoding(encoding);
			}
			HttpServletRequest req = (HttpServletRequest) request;
			if(req.getMethod().equalsIgnoreCase("get")){
				encoding(req);
			}
		}
		chain.doFilter(request, response);
	}

	private String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore = true;
        else
            this.ignore = false;
	}

}
