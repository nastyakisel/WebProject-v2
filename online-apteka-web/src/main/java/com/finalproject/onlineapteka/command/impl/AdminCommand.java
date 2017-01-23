package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.finalproject.onlineapteka.command.Command;


public class AdminCommand implements Command{
	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Command getAllGoods = new GetAllDrugsSessionCommand();
		getAllGoods.execute(request, response);

		response.sendRedirect("administratorPage.jsp");
	}
}
