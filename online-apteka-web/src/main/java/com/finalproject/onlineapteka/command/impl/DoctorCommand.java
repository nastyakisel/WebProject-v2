package com.finalproject.onlineapteka.command.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class DoctorCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();

		RecipeService recipeService = ServiceFactory.getInstance()
				.getRecipeService();
		List<Recipe> recipeList = new ArrayList<>();

		recipeList = recipeService.getAllRecipes();

		session.setAttribute("recipeList", recipeList);
		response.sendRedirect("doctorPage.jsp");
	}
}
