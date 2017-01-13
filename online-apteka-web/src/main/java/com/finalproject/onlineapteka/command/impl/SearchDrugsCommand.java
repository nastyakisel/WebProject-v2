package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class SearchDrugsCommand implements Command{
	private static final Logger LOGGER = LogManager.getLogger(SearchDrugsCommand.class.getName());
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String drugName = request.getParameter("search");
		List<Drug> drugList = null;
		DrugService drugService = (DrugService) ServiceFactory.getInstance()
				.getGoodsService();
		
		try {
			drugList = drugService.getDrugsByName(drugName);
		} catch (ServiceException e) {
			LOGGER.error("Failed recieving the drugs", e);
		}
		
		request.setAttribute("drug_List", drugList);
		
		Command categoryCommand = new GetCategoriesCommand();
		categoryCommand.execute(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("start.jsp");
		dispatcher.forward(request, response);
	}
}
