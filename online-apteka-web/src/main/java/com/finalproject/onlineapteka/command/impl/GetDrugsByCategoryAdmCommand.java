package com.finalproject.onlineapteka.command.impl;


import java.util.List;
import javax.servlet.RequestDispatcher;
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

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		Integer categotyId = Integer.parseInt(request.getParameter("catId"));

		List<Drug> drugList = null;
		List<Category> categoryList = null;
		DrugService service = (DrugService) ServiceFactory.getInstance()
				.getDrugService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		drugList = service.getGrugsByCategory(categotyId, requestLocale);
		categoryList = categoryService.getAllCategories(requestLocale);

		session.setAttribute("categotyId", categotyId);

		request.setAttribute("drug_List", drugList);
		request.setAttribute("category_List", categoryList);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("administratorPage_2.jsp");
		dispatcher.forward(request, response);
	}
}
