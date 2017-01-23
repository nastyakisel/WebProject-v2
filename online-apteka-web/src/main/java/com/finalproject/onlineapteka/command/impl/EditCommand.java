package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class EditCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(EditCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer goodId = Integer.parseInt(request.getParameter("drugId"));
		String requestLocale = (String) session.getAttribute("requestLocale");
		if (requestLocale == null) {
			requestLocale = "ru";
		}
		Drug recievedDrug = null;
		DrugService service = (DrugService) ServiceFactory.getInstance()
				.getGoodsService();

		try {
			recievedDrug = service.getDrugById(goodId, requestLocale);
		} catch (ServiceException e) {
			LOGGER.error("Failed receiving the good", e);
		}

		String previousURI = request.getHeader("referer");
		request.setAttribute("previousURI", previousURI);
		request.setAttribute("drug", recievedDrug);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		dispatcher.forward(request, response);
	}
}
