package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.command.Command;

public class LocaleCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(LocaleCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestLocale = request.getParameter("action");
		String previousURL = request.getHeader("referer");
		HttpSession session = request.getSession();
		session.setAttribute("requestLocale", requestLocale);
		
		try {
			response.sendRedirect(previousURL);
			System.out.println("g");
		} catch (IOException e) {
			LOGGER.error("LocaleError", e);
		}
		
	}
}
