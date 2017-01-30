package com.finalproject.onlineapteka.command.impl;

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

	UserService userService = ServiceFactory.getInstance().getUserService();
	RecipeService recipeService = ServiceFactory.getInstance()
			.getRecipeService();
	RecipeDetailService recipeDetailService = ServiceFactory.getInstance()
			.getRecipeDetailService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		Integer recipeId = getParameterFromRequestAsInteger("recipeId", request);
		
		Integer userId = (Integer) session.getAttribute("userId");
	
		User user = userService.getUserById(userId);

		Integer role = user.getRoleId();
		
		Recipe recipe = recipeService.getRecipeById(recipeId);
		User userInRecipe = userService.getUserById(recipe.getUserId());
		List<Drug> drugListInRecipe = recipeDetailService.getDrugsFromRecipe(recipeId, requestLocale);

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
