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
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class EditCommand implements Command {
	private static final Logger logger = LogManager.getLogger(EditCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer goodId = Integer.parseInt(request.getParameter("drugId"));

		Drug recievedDrug = null;
		GoodsService service = (GoodsService) ServiceFactory.getInstance()
				.getGoodsService();

		try {
			recievedDrug = service.getGoodById(goodId);
		} catch (ServiceException e) {
			logger.error("Failed receiving the good", e);
		}

		HttpSession session = request.getSession();
		String previousURI = request.getHeader("referer");
		request.setAttribute("previousURI", previousURI);
		request.setAttribute("drug", recievedDrug);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		dispatcher.forward(request, response);
	}
}
