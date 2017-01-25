package com.finalproject.onlineapteka.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.command.Command;

public class LocaleCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		String locale = request.getParameter("action");
		String previousURL = request.getHeader("referer");
		HttpSession session = request.getSession();
		session.setAttribute("requestLocale", locale);

		response.sendRedirect(previousURL);

	}
}
