package com.finalproject.onlineapteka.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalproject.onlineapteka.command.Command;


@WebServlet("/controller.html")
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final CommandProvider provider = new CommandProvider();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String commandName = request.getParameter("action");

		Command command = provider.getCommand(commandName);
			
		command.execute(request, response);
		}
	}

