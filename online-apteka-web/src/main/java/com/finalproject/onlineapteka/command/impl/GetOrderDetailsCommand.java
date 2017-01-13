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
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.OrderDetailService;
import com.finalproject.onlineapteka.service.OrderService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetOrderDetailsCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(GetOrderDetailsCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = (OrderService) ServiceFactory.getInstance()
				.getOrderService();
		OrderDetailService orderDetailService = (OrderDetailService) ServiceFactory.getInstance()
				.getOrderDetailService();
		CartService cartService = (CartService) ServiceFactory.getInstance()
				.getCartService();
		UserService userService = ServiceFactory.getInstance().getUserService();

		HttpSession session = request.getSession();
		String customOrderId = request.getParameter("orderId");
		List<Drug> drugsInOrder = new ArrayList<>();
		try {
			drugsInOrder = orderDetailService.getDrugsFromOrderDetail(Integer.parseInt(customOrderId));
		} catch (ServiceException e) {
			LOGGER.error("Failed loading the drugs from orderDetail", e);
		}
		session.setAttribute("drugsInCustomOrder", drugsInOrder);
		session.setAttribute("customOrderId", customOrderId);
		response.sendRedirect("orderDetails.jsp");
	}
}
