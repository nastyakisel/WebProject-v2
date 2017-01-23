package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class DeleteCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(DeleteCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer drugId = Integer.parseInt(request.getParameter("drugId"));
		String requestLocale = (String) session.getAttribute("requestLocale");
		if (requestLocale == null) {
			requestLocale = "ru";
		}
		
		DrugService drugService = (DrugService) ServiceFactory.getInstance()
				.getGoodsService();

		try {
			drugService.removeDrug(drugId);;
		} catch (ServiceException e) {
			LOGGER.error("Failed deleting the good", e);
		}

		Command getAllGoods = new AdminCommand();
		getAllGoods.execute(request, response);
	}
}
