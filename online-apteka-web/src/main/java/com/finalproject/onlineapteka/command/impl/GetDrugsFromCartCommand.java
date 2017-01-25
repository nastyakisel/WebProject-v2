package com.finalproject.onlineapteka.command.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetDrugsFromCartCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		CartService cartService = ServiceFactory.getInstance().getCartService();

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

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

			shoppingCart = cartService.getDrugsFromCart(userId, requestLocale);

			session.setAttribute("has_errors", null);
			session.setAttribute("shoppingCart", shoppingCart);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("cart.jsp");
			dispatcher.forward(request, response);
		}
	}

}