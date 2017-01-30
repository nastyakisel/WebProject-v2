package com.finalproject.onlineapteka.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class AssignRecipeCommand extends Command {
	
	UserService userService = ServiceFactory.getInstance().getUserService();
	DrugService drugService = (DrugService) ServiceFactory.getInstance()
			.getDrugService();
	
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();

		Integer resipeId = (Integer) session.getAttribute("recipeId");
		if (resipeId != null) {
			session.setAttribute("recipeId", null);
		}
		
		User selectedUser = (User) session.getAttribute("selectedUser");
		if (selectedUser != null) {
			session.setAttribute("selectedUser", null);
		}
		
		List<User> clientList = userService.getUsersByRole(1);

		List<Drug> drugListWhithRecipe = drugService.getDrugsByRecipe(1, requestLocale);
		
		session.setAttribute("drugListWhithRecipe", drugListWhithRecipe);
		session.setAttribute("clientList", clientList);
		response.sendRedirect("assignRecipe.jsp");
	}
}
