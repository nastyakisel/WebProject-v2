package com.finalproject.onlineapteka.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetDrugsByCategoryAdmCommand extends Command {

	DrugService drugService = (DrugService) ServiceFactory.getInstance()
			.getDrugService();
	CategoryService categoryService = ServiceFactory.getInstance()
			.getCategoryService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		Integer categotyId = getParameterFromRequestAsInteger("catId", request);

		List<Drug> drugList = drugService.getGrugsByCategory(categotyId, requestLocale);
		List<Category> categoryList = categoryService.getAllCategories(requestLocale);

		session.setAttribute("categotyId", categotyId);

		request.setAttribute("drug_List", drugList);
		request.setAttribute("category_List", categoryList);
		
		forward("administratorPage_2.jsp", request, response);
	}
}
