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
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GoodDetailsCommand implements Command {
	private static final Logger logger = LogManager.getLogger(EditCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer goodId = Integer.parseInt(request.getParameter("goodId"));

		Drug recievedDrug = null;
		List<Category> categoryList = null;
		GoodsService goodService = (GoodsService) ServiceFactory.getInstance()
				.getGoodsService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		try {
			recievedDrug = goodService.getGoodById(goodId);
			categoryList = categoryService.getAllCategories();
		} catch (ServiceException e) {
			logger.error("Failed receiving the good", e);
		}

		HttpSession session = request.getSession();

		request.setAttribute("drug", recievedDrug);
		request.setAttribute("category_List", categoryList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("goodDetail.jsp");
		dispatcher.forward(request, response);
	}
}
