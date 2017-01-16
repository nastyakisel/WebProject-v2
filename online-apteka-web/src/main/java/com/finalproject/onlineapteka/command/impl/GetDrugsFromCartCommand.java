package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetDrugsFromCartCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(GetDrugsFromCartCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CartService cartService = ServiceFactory.getInstance()
 				.getCartService();

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String requestLocale = (String) session.getAttribute("requestLocale");
		if (requestLocale == null) {
			requestLocale = "ru";
		}

		if (userId == null) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("cart.jsp");
			dispatcher.forward(request, response);
		}

		else {
			List<Drug> shoppingCart = (List<Drug>) session
					.getAttribute("shoppingCart");

			if (shoppingCart == null) {
				shoppingCart = new ArrayList<Drug>();
			}
			try {
				shoppingCart = cartService.getDrugsFromCart(userId, requestLocale);
			} catch (ServiceException e) {
				LOGGER.error("Failed receiving from cart", e);
			}
			session.setAttribute("shoppingCart", shoppingCart);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("cart.jsp");
			dispatcher.forward(request, response);
		}
	}

}