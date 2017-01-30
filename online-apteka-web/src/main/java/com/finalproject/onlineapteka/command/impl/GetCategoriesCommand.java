package com.finalproject.onlineapteka.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetCategoriesCommand extends Command {

	CategoryService categoryService = ServiceFactory.getInstance()
			.getCategoryService();
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		
		List<Category> categoryList = categoryService.getAllCategories();

		session.setAttribute("category_List", categoryList);
	}
}
