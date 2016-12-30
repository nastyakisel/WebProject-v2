package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.command.Command;

public class LocaleCommand implements Command {
	private static final Logger logger = LogManager.getLogger(EditCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestLocale = request.getParameter("action");
		String previousURL = request.getHeader("referer");
		HttpSession session = request.getSession();
		session.setAttribute("requestLocale", requestLocale);
		
		try {
			response.sendRedirect(previousURL);
			System.out.println("g");
		} catch (IOException e) {
			logger.error("LocaleError", e);
		}
		
	}
}
