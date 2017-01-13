package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetAllDrugsCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(EditCommand.class
			.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String requestLocale = (String) session.getAttribute("requestLocale");
		List<Drug> drugList = null;
		List<Category> categoryList = null;
		DrugService goodsService = (DrugService) ServiceFactory.getInstance()
				.getGoodsService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		if (requestLocale == null) {
			requestLocale = "ru";
		}

		try {
			drugList = goodsService.getAllDrugs(requestLocale);
			categoryList = categoryService.getAllCategories(requestLocale);
		} catch (ServiceException e) {
			LOGGER.error("Failed recieving the good", e);
		}

		request.setAttribute("drug_List", drugList);
		request.setAttribute("category_List", categoryList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("start.jsp");
		dispatcher.forward(request, response);
	}
}