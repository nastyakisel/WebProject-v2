package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalproject.onlineapteka.command.Command;

public class AddCommand extends Command {
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {
		
		RequestDispatcher dispatcher;
		
		String previousURI = request.getHeader("referer");
		request.setAttribute("previousURI", previousURI);
		dispatcher = request.getRequestDispatcher("addGood.jsp");
		dispatcher.forward(request, response);
	}
}
