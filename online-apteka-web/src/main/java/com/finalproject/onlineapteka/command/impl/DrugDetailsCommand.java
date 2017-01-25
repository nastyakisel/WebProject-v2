package com.finalproject.onlineapteka.command.impl;


import java.util.List;
import javax.servlet.RequestDispatcher;
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

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		Integer goodId = Integer.parseInt(request.getParameter("goodId"));
		HttpSession session = request.getSession();

		Drug recievedDrug = null;
		List<Category> categoryList = null;
		DrugService goodService = (DrugService) ServiceFactory.getInstance()
				.getDrugService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		recievedDrug = goodService.getDrugById(goodId, requestLocale);
		categoryList = categoryService.getAllCategories(requestLocale);

		request.setAttribute("drug", recievedDrug);
		request.setAttribute("category_List", categoryList);

		Integer userId = (Integer) session.getAttribute("userId");
		if (userId != null) {
			UserService userService = ServiceFactory.getInstance()
					.getUserService();
			User user = null;

			user = userService.getUserById(userId);

			if (user.getRoleId() == 2) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("goodDetailAdmin.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("goodDetail.jsp");
		dispatcher.forward(request, response);
	}
}
