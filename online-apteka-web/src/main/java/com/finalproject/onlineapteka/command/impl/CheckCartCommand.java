package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class CheckCartCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(CheckCartCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CartService cartService = ServiceFactory.getInstance()
				.getCartService();
		UserService userService = ServiceFactory.getInstance().getUserService();
		
		HttpSession session = request.getSession();
		String requestLocale = (String) session.getAttribute("requestLocale");
		if (requestLocale == null) {
			requestLocale = "ru";
		}
		Integer userId = (Integer) session.getAttribute("userId");
		List<Drug> shoppingCart = (List<Drug>) session
				.getAttribute("shoppingCart");
		
		String count = request.getParameter("count");
		String delete = request.getParameter("delete");
		String buy = request.getParameter("buy");
		String[] drugId = request.getParameterValues("drugId");
		String[] drugQuantity = request.getParameterValues("quantity");
		String[] drugsToDelete = request.getParameterValues("checkbox");
		
		if (count != null) {
			if (userId != null) {
				for (int k = 0; k < drugId.length; k++) {
					Cart cart = new Cart();
					cart.setQuantity(Float.valueOf(drugQuantity[k]));
					cart.setDrugId(Integer.parseInt(drugId[k]));
					cart.setUserId(userId);
					try {
						cartService.changeCart(cart);
					} catch (ServiceException e) {
						LOGGER.error("Failed updating the cart", e);
					}
				} // for
				try {
					shoppingCart = cartService.getDrugsFromCart(userId, requestLocale);
				} catch (ServiceException e) {
					LOGGER.error("Failed receiving from the cart", e);
				}
				session.setAttribute("shoppingCart", shoppingCart);
			} // if (userId != null)
			else {
				for (int j = 0; j < shoppingCart.size(); j++) {
					for (int i = 0; i < drugId.length; i++) {
						if (shoppingCart.get(j).getId() == Integer
								.parseInt(drugId[i])) {
							shoppingCart.get(j).setQuantity(
									Float.valueOf(drugQuantity[i]));
						}
					}
				}
			}
			response.sendRedirect("cart.jsp");
		} // if (count != null)

		if (delete != null) {
			if (userId == null) {

				for (int i = 0; i < drugsToDelete.length; i++) {
					for (int j = 0; j < shoppingCart.size(); j++) {
						if (Integer.parseInt(drugsToDelete[i]) == shoppingCart
								.get(j).getId()) {
							shoppingCart.remove(j);
						}
					}
				}
			} // if (userId == null)
			else {

				for (int i = 0; i < drugsToDelete.length; i++) {
					try {
						cartService.removeDrugFromCart(
								Integer.parseInt(drugsToDelete[i]), userId);
						shoppingCart = cartService.getDrugsFromCart(userId, requestLocale);
					} catch (NumberFormatException | ServiceException e) {
						LOGGER.error("Failed delete from cart", e);
					}
				}
				session.setAttribute("shoppingCart", shoppingCart);
			} // else
			response.sendRedirect("cart.jsp");
		} // if (delete != null)

		if (buy != null) {
			User user = null;
			try {
				user = userService.getUserById(userId);
			} catch (ServiceException e) {
				LOGGER.error("Failed receiving the user", e);
			}
			session.setAttribute("user", user);
			response.sendRedirect("invoice.jsp");
		} // if (buy != null)
	}
}
