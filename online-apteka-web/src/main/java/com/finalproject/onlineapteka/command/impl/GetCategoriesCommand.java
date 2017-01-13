package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetCategoriesCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(GetCategoriesCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Category> categoryList = null;
		
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		try {
			categoryList = categoryService.getAllCategories();
		} catch (ServiceException e) {
			LOGGER.error("Failed recieving the good", e);
		}

		request.setAttribute("category_List", categoryList);
		
	}
}
