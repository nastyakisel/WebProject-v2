package com.finalproject.onlineapteka.command.impl;


import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetAllDrugsCommand extends Command {
	
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {

		List<Drug> drugList = null;
		List<Category> categoryList = null;
		DrugService goodsService = (DrugService) ServiceFactory.getInstance()
				.getDrugService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		drugList = goodsService.getAllDrugs(requestLocale);
		categoryList = categoryService.getAllCategories(requestLocale);

		request.setAttribute("drug_List", drugList);
		request.setAttribute("category_List", categoryList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("start.jsp");
		dispatcher.forward(request, response);
	}
}
