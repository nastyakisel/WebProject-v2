package com.finalproject.onlineapteka.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetUserRecipesCommand extends Command {

	RecipeService recipeService = ServiceFactory.getInstance()
			.getRecipeService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		List<Recipe> recipeList = recipeService.getRecipesByUser(userId);

		session.setAttribute("userRecipeList", recipeList);
		response.sendRedirect("userRecipeList.jsp");
	}
}
