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

public class GetAllDrugsSessionCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(GetAllDrugsSessionCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Drug> drugList = null;
		List<Category> categoryList = null;
		DrugService goodsService = (DrugService) ServiceFactory.getInstance()
				.getGoodsService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		try {
			drugList = goodsService.getAllDrugs();
			categoryList = categoryService.getAllCategories();
		} catch (ServiceException e) {
			LOGGER.error("Failed receiving the good", e);

		}
		
		session.setAttribute("drug_List", drugList);
		session.setAttribute("category_List", categoryList);

		
	}
}
