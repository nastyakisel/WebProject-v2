package com.finalproject.onlineapteka.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class DrugDetailsCommand extends Command {

	DrugService drugService = (DrugService) ServiceFactory.getInstance()
			.getDrugService();
	CategoryService categoryService = ServiceFactory.getInstance()
			.getCategoryService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		Integer goodId = getParameterFromRequestAsInteger("goodId", request);
		
		HttpSession session = request.getSession();

		Drug recievedDrug = drugService.getDrugById(goodId, requestLocale);
		List<Category> categoryList = categoryService.getAllCategories(requestLocale);

		request.setAttribute("drug", recievedDrug);
		request.setAttribute("category_List", categoryList);

		Integer userId = (Integer) session.getAttribute("userId");
		if (isUserAuthentificated(userId)) {
			UserService userService = ServiceFactory.getInstance()
					.getUserService();
			
			User user = userService.getUserById(userId);

			if (user.getRoleId() == 2) {
				forward("goodDetailAdmin.jsp", request, response);
				return;
			}
		}
		forward("goodDetail.jsp", request, response);
	}
	
	private boolean isUserAuthentificated(Integer userId) {
		if (userId == null) {
			return false;
		}
		return true;
	}
}
