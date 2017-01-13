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

import com.finalproject.onlineapteka.bean.CustomOrder;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.OrderService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class AssignRecipeCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(AssignRecipeCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService userService = ServiceFactory.getInstance().getUserService();

		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");
		Integer resipeId = (Integer) session.getAttribute("recipeId");
		if (resipeId != null) {
			session.setAttribute("recipeId", null);
		}
		
		User selectedUser = (User) session.getAttribute("selectedUser");
		if (selectedUser != null) {
			session.setAttribute("selectedUser", null);
		}
		
		List<User> clientList = new ArrayList<User>();
		try {
			clientList = userService.getUsersByRole(1);
		} catch (ServiceException e) {
			LOGGER.error("Failed receving the users", e);
		}
		DrugService goodsService = (DrugService) ServiceFactory.getInstance()
				.getGoodsService();
		List<Drug> drugListWhithRecipe = new ArrayList<Drug>();
		try {
			drugListWhithRecipe = goodsService.getDrugsByRecipe(1);
		} catch (ServiceException e) {
			LOGGER.error("Failed receiving the goods", e);
		}

		session.setAttribute("drugListWhithRecipe", drugListWhithRecipe);
		session.setAttribute("clientList", clientList);
		response.sendRedirect("assignRecipe.jsp");
	}
}
