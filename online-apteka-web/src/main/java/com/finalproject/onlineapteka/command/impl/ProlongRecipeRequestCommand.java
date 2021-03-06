package com.finalproject.onlineapteka.command.impl;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class ProlongRecipeRequestCommand extends Command {

	RecipeService recipeService = ServiceFactory.getInstance()
			.getRecipeService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		String prevURI = request.getHeader("referer");
		String recipeId = request.getParameter("recipeId");

		recipeService.updateRecipeRequest(1,
				new Date(System.currentTimeMillis()),
				Integer.parseInt(recipeId));

		Recipe recipe = recipeService.getRecipeById(Integer.parseInt(recipeId));
		if (recipe.getHasRequest() != 1) {

			response.sendRedirect(prevURI);
			return;
		}
		response.sendRedirect("recipeRequestConformation.jsp");
	}
}
