package com.finalproject.onlineapteka.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class DeleteCommand extends Command {
	
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {

		Integer drugId = Integer.parseInt(request.getParameter("drugId"));
		
		DrugService drugService = (DrugService) ServiceFactory.getInstance()
				.getDrugService();

		drugService.removeDrug(drugId);;
		
		Command getAllGoods = new AdminCommand();
		getAllGoods.execute(request, response);
	}
}
