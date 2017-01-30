package com.finalproject.onlineapteka.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetDrugsFromCartCommand extends Command {

	CartService cartService = ServiceFactory.getInstance().getCartService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (!isUserAuthentificated(userId)) {
			forward("cart.jsp", request, response);
		}

		else {
			List<Drug> shoppingCart = (List<Drug>) session
					.getAttribute("shoppingCart");

			if (shoppingCart == null) {
				shoppingCart = cartService.getDrugsFromCart(userId, requestLocale);
			}

			session.setAttribute("has_errors", null);
			session.setAttribute("shoppingCart", shoppingCart);
			forward("cart.jsp", request, response);
		}
	}
	private boolean isUserAuthentificated(Integer userId) {
		if (userId == null) {
			return false;
		}
		return true;
	}
}