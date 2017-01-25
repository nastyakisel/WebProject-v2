package com.finalproject.onlineapteka.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Command {
	private static final Logger LOGGER = LogManager
			.getLogger(Command.class.getName());

	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String requestLocale = (String) session.getAttribute("requestLocale");
		if (requestLocale == null) {
			requestLocale = "ru";
		}
		try {
			handle(request, response, requestLocale);
		} catch (Exception e) {
			LOGGER.error("Failed to implement the method handle", e);
		}
	}

	public abstract void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception;
}
