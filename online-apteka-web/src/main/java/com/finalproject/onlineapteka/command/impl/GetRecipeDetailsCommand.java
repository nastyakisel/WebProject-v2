package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeDetailService;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetRecipeDetailsCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(GetRecipeDetailsCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		RecipeService recipeService = ServiceFactory.getInstance()
				.getRecipeService();
		RecipeDetailService recipeDetailService = ServiceFactory.getInstance()
				.getRecipeDetailService();

		HttpSession session = request.getSession();
		String recipeId = request.getParameter("recipeId");
		
		Recipe recipe = null;
		List<Drug> drugListInRecipe = new ArrayList<>();
		User userInRecipe = new User();
			try {
				recipe = recipeService.getRecipeById(Integer.parseInt(recipeId));
				userInRecipe = userService.getUserById(recipe.getUserId());
				drugListInRecipe = recipeDetailService.getDrugsFromRecipe(Integer.parseInt(recipeId));
			} catch (NumberFormatException e) {
				LOGGER.error("NumberFormatException", e);
			} catch (ServiceException e) {
				LOGGER.error("Failed receiving the recipe", e);
			}
		
		session.setAttribute("recipe", recipe);
		session.setAttribute("userInRecipe", userInRecipe);
		session.setAttribute("drugListInRecipe", drugListInRecipe);
		response.sendRedirect("recipeDetails.jsp");
	}
}
