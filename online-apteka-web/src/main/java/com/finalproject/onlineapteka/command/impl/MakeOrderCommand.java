package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
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
import com.finalproject.onlineapteka.bean.OrderDetail;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.OrderDetailService;
import com.finalproject.onlineapteka.service.OrderService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class MakeOrderCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(MakeOrderCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = (OrderService) ServiceFactory.getInstance()
				.getOrderService();
		OrderDetailService orderDetailService = (OrderDetailService) ServiceFactory.getInstance()
				.getOrderDetailService();
		CartService cartService = (CartService) ServiceFactory.getInstance()
				.getCartService();

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		List<Drug> shoppingCart = (List<Drug>) session
				.getAttribute("shoppingCart");
		String totalPrice = request.getParameter("sumTotal");

		CustomOrder customOrder = new CustomOrder();
		Date orderDate = new Date(System.currentTimeMillis());
		
		customOrder.setOrderDate(orderDate);
		customOrder.setTotalPrice(BigDecimal.valueOf(Double.valueOf(totalPrice)));
		customOrder.setUserId(userId);
		customOrder.setOrderStatus(0);
		Integer customOrderId = 0;
		try {
			customOrderId = orderService.addOrder(customOrder);
		} catch (ServiceException e) {
			LOGGER.error("Failed to create the order", e);
		}

		for (int i = 0; i < shoppingCart.size(); i++) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setDrugId(shoppingCart.get(i).getId());
			orderDetail.setQuantity(shoppingCart.get(i).getQuantity());
			BigDecimal bigDecimalQuantity = BigDecimal.valueOf(Double.valueOf(shoppingCart.get(i).getQuantity()));
			BigDecimal price = shoppingCart.get(i).getPrice();
			BigDecimal totalPriceOfGood = price.multiply(bigDecimalQuantity);
			orderDetail.setTotalPriceOfGood(totalPriceOfGood);
			orderDetail.setCustomOrderId(customOrderId);
			try {
				orderDetailService.addOrderDetail(orderDetail);
			} catch (ServiceException e) {
				LOGGER.error("Failed to create the orderDetail", e);
			}
		}// for
		session.setAttribute("shoppingCart", null);
											// получить данные из заказа
		try {
			cartService.removeCart(userId);
		} catch (ServiceException e) {
			LOGGER.error("Failed to delete the cart", e);
		}
		
		List<Drug> drugsInOrder = new ArrayList<>();
		try {
			drugsInOrder = orderDetailService.getDrugsFromOrderDetail(customOrderId);
		} catch (ServiceException e) {
			LOGGER.error("Failed loading the drugs from orderDetail", e);
		}
		session.setAttribute("drugsInOrder", drugsInOrder);
		session.setAttribute("customOrderId", customOrderId);
		session.setAttribute("totalPrice", totalPrice);
		response.sendRedirect("orderConfirmation.jsp");
	} // execute
}
