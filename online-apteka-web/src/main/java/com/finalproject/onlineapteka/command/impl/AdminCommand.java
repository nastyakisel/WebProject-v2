package com.finalproject.onlineapteka.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finalproject.onlineapteka.command.Command;


public class AdminCommand extends Command{
	
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {

		Command getAllGoods = new GetAllDrugsSessionCommand();
		getAllGoods.execute(request, response);

		response.sendRedirect("administratorPage.jsp");
	}
}
