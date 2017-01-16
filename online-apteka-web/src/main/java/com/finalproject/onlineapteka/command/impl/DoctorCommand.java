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

import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class DoctorCommand implements Command{
	private static final Logger LOGGER = LogManager
			.getLogger(DoctorCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		RecipeService recipeService = ServiceFactory.getInstance()
				.getRecipeService();
		List<Recipe> recipeList = new ArrayList<>();
		try {
			recipeList = recipeService.getAllRecipes();
		} catch (ServiceException e) {
			LOGGER.error("Failed receving the recipe", e);
		}

		session.setAttribute("recipeList", recipeList);
		response.sendRedirect("doctorPage.jsp");
	}
}
