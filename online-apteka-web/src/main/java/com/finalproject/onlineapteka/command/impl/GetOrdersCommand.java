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
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.OrderService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetOrdersCommand implements Command{
	private static final Logger LOGGER = LogManager
			.getLogger(GetOrdersCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = (OrderService) ServiceFactory.getInstance()
				.getOrderService();
		UserService userService = ServiceFactory.getInstance().getUserService();

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		List<CustomOrder> customOrderList = new ArrayList<CustomOrder>();
		try {
			customOrderList = orderService.getOrdersByUserId(userId);
		} catch (ServiceException e) {
			LOGGER.error("Failed receiving the orders", e);
		}
		
		User user = null;
		try {
			user = userService.getUserById(userId);
		} catch (ServiceException e) {
			LOGGER.error("Failed receiving the user", e);
		}
		session.setAttribute("user", user);
		session.setAttribute("customOrderList", customOrderList);
		response.sendRedirect("orders.jsp");
	}
}
