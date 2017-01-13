package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class ProlongRecipeCommand implements Command{
	private static final Logger LOGGER = LogManager
			.getLogger(CreateRecipeCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RecipeService recipeService = ServiceFactory.getInstance()
				.getRecipeService();

		HttpSession session = request.getSession();

		String endDate = request.getParameter("new_end_date");
		
		Recipe recipe = (Recipe) session.getAttribute("recipe");
		
		try {
			recipeService.updateRecipe(Date.valueOf(endDate), recipe.getId());
			recipe = recipeService.getRecipeById(recipe.getId());
		} catch (ServiceException e) {
			LOGGER.error("Failed apdating the recipe", e);

		}
		session.setAttribute("recipe", recipe);
		response.sendRedirect("recipeDetails.jsp");
		
	}
}
