package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.sql.SQLException;
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

public class GetDrugsByCategoryAdmCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(GetDrugsByCategoryAdmCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer categotyId = Integer.parseInt(request.getParameter("catId"));

		List<Drug> drugList = null;
		List<Category> categoryList = null;
		DrugService service = (DrugService) ServiceFactory.getInstance()
				.getGoodsService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		try {
			drugList = service.getGrugsByCategory(categotyId);
			categoryList = categoryService.getAllCategories();
		} catch (ServiceException e) {
			LOGGER.error("Failed receiving the good", e);
		}

		HttpSession session = request.getSession();
		session.setAttribute("categotyId", categotyId);

		request.setAttribute("drug_List", drugList);
		request.setAttribute("category_List", categoryList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("administratorPage_2.jsp");
		dispatcher.forward(request, response);
	}
}
