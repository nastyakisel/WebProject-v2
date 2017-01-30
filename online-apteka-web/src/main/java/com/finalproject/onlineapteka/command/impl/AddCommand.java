package com.finalproject.onlineapteka.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finalproject.onlineapteka.command.Command;

public class AddCommand extends Command {
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {
		
		String addById = request.getParameter("byId");
		request.setAttribute("addById", addById);
		
		String previousURI = request.getHeader("referer");
		request.setAttribute("previousURI", previousURI);
		
		forward("addGood.jsp", request, response);
	}
}
