package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.command.Command;

public class AddCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		
		String previousURI = request.getHeader("referer");
		request.setAttribute("previousURI", previousURI);
		dispatcher = request.getRequestDispatcher("addGood.jsp");
		dispatcher.forward(request, response);
	}
}
