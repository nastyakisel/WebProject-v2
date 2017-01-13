package com.finalproject.onlineapteka.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BasicFilter implements Filter{
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
		request.setCharacterEncoding("UTF-8");
		System.out.println("Hallo Filter");
		chain.doFilter(request, response);
		
	}

}
