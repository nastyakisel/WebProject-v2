package com.finalproject.onlineapteka.command.impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.command.Command;

public class LogOutCommand extends Command {
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
}
