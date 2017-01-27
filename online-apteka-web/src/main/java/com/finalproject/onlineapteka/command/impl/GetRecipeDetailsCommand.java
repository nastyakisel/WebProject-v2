package com.finalproject.onlineapteka.command.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeDetailService;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetRecipeDetailsCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		UserService userService = ServiceFactory.getInstance().getUserService();
		RecipeService recipeService = ServiceFactory.getInstance()
				.getRecipeService();
		RecipeDetailService recipeDetailService = ServiceFactory.getInstance()
				.getRecipeDetailService();

		HttpSession session = request.getSession();
		String recipeId = request.getParameter("recipeId");
		Integer userId = (Integer) session.getAttribute("userId");
		User user = null;

		user = userService.getUserById(userId);

		Integer role = user.getRoleId();
		Recipe recipe = null;
		List<Drug> drugListInRecipe = new ArrayList<>();
		User userInRecipe = new User();

		recipe = recipeService.getRecipeById(Integer.parseInt(recipeId));
		userInRecipe = userService.getUserById(recipe.getUserId());
		drugListInRecipe = recipeDetailService.getDrugsFromRecipe(Integer
				.parseInt(recipeId), requestLocale);

		session.setAttribute("recipe", recipe);
		session.setAttribute("userInRecipe", userInRecipe);
		session.setAttribute("drugListInRecipe", drugListInRecipe);
		if (role == 1) {
			response.sendRedirect("userRecipeDetails.jsp");
		}
		if (role == 3) {
			response.sendRedirect("recipeDetails.jsp");
		}
	}
}
