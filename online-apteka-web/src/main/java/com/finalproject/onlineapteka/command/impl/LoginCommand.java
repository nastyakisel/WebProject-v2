package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class LoginCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class
			.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		HttpSession session = request.getSession();

		UserService userService = ServiceFactory.getInstance().getUserService();
		User receivedUser = null;

		try {
			receivedUser = userService.getUser(userName, password);
		} catch (ServiceException e) {
			LOGGER.error("Failed receiving the user", e);
		}

		List<ErrorBean> userErrors = validateUser(receivedUser);
		
		
		if (!userErrors.isEmpty()) {
			session.setAttribute("has_errors", userErrors);
			session.setAttribute("current_user", user);
			response.sendRedirect("login.jsp");

			return;
		}

		session.setAttribute("userId", receivedUser.getId());

		Command getAllGoods = new GetAllDrugsSessionCommand();
		getAllGoods.execute(request, response);
		
		List<Drug> shoppingCart = (List<Drug>) session
				.getAttribute("shoppingCart");
		DrugService goodsService = (DrugService) ServiceFactory.getInstance()
				.getGoodsService();

		if (shoppingCart != null) {  
			for (int i = 0; i < shoppingCart.size(); i++) {
				try {
					goodsService.addDrugToCart(shoppingCart.get(i).getId(),
							receivedUser.getId());
				} catch (ServiceException e) {
					LOGGER.error("Failed adding to cart", e);
				}
			}
		}
		
		Integer roleId = receivedUser.getRoleId();
		
		if (roleId == 3) {
			RecipeService recipeService = ServiceFactory.getInstance()
					.getRecipeService();
			List<Recipe> recipeList = new ArrayList<>();
			try {
				recipeList = recipeService.getAllRecipes();
			} catch (ServiceException e) {
				LOGGER.error("Failed receving the recipe", e);
			}
			
			session.setAttribute("recipeList", recipeList);
			session.setAttribute("doctorUser", receivedUser);
			response.sendRedirect("doctorPage.jsp");
		} // if (roleId == 3)
		else {
			response.sendRedirect(getURI(roleId));
		}
		
	}

	public String getURI(Integer role) {
		Map<String, String> roleMap = new HashMap<String, String>();
		roleMap.put("1", "start.jsp");
		roleMap.put("2", "administratorPage.jsp");
		//roleMap.put("3", "doctorPage.jsp");

		String uri = roleMap.get(role.toString());
		return uri;

	}
	private List<ErrorBean> validateUser(User user) {
		List<ErrorBean> errors = new ArrayList<ErrorBean>();
		if (user == null) {
			ErrorBean errorName = new ErrorBean("loginPage.errorUser");
			errors.add(errorName);
		}
		
		return errors;
	}
}
