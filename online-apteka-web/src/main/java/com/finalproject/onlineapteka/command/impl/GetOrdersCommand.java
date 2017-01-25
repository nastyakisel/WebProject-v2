package com.finalproject.onlineapteka.command.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.CustomOrder;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.OrderService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetOrdersCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {
		OrderService orderService = (OrderService) ServiceFactory.getInstance()
				.getOrderService();
		UserService userService = ServiceFactory.getInstance().getUserService();

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		List<CustomOrder> customOrderList = new ArrayList<CustomOrder>();

		customOrderList = orderService.getOrdersByUserId(userId);

		User user = null;

		user = userService.getUserById(userId);

		session.setAttribute("user", user);
		session.setAttribute("customOrderList", customOrderList);
		response.sendRedirect("orders.jsp");
	}
}
