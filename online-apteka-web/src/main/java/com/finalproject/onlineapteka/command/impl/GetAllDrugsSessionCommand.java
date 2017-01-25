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

public class GetAllDrugsSessionCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();

		List<Drug> drugList = null;
		List<Category> categoryList = null;
		DrugService goodsService = (DrugService) ServiceFactory.getInstance()
				.getDrugService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		drugList = goodsService.getAllDrugs(requestLocale);
		categoryList = categoryService.getAllCategories(requestLocale);

		session.setAttribute("drug_List", drugList);
		session.setAttribute("category_List", categoryList);

	}
}
