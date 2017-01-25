package com.finalproject.onlineapteka.command.impl;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class ProlongRecipeCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		RecipeService recipeService = ServiceFactory.getInstance()
				.getRecipeService();

		HttpSession session = request.getSession();
		String prevURI = request.getHeader("referer");

		String endDate = request.getParameter("new_end_date");
		if (endDate.isEmpty()) {
			response.sendRedirect(prevURI);
			return;
		}
		Recipe recipe = (Recipe) session.getAttribute("recipe");
		recipeService.updateRecipe(Date.valueOf(endDate), recipe.getId());
		recipe = recipeService.getRecipeById(recipe.getId());

		session.setAttribute("recipe", recipe);
		response.sendRedirect("recipeDetails.jsp");

	}
}
