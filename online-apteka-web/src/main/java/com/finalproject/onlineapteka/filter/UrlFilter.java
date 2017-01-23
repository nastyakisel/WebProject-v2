package com.finalproject.onlineapteka.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.User;

public class UrlFilter implements Filter{
	private FilterConfig filterConfig;
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter initialized");
		this.filterConfig = filterConfig;
	}
	public void destroy() {
		System.out.println("Filter destroyed");
		this.filterConfig = null;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Hallo UrlFilter");
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("start.jsp");
		HttpSession session = ((HttpServletRequest) request).getSession();
		User doctorUser = (User) session.getAttribute("doctorUser");
		if (doctorUser == null) {
			dispatcher.forward(request, response);
		}
		
		chain.doFilter(request, response);
		
	}
	

}
