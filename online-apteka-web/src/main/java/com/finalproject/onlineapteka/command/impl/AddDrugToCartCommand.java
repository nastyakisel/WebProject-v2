package com.finalproject.onlineapteka.command.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;
import com.finalproject.onlineapteka.command.Command;

public class AddDrugToCartCommand extends Command {

	DrugService goodService = (DrugService) ServiceFactory.getInstance()
			.getDrugService();
	CartService cartService = ServiceFactory.getInstance().getCartService();

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		Integer drugId = getParameterFromRequestAsInteger("drugId", request);
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String previousURI = request.getHeader("referer");

		if (!isUserAuthentificated(userId)) {
			
			List<Drug> shoppingCart = (List<Drug>) session
					.getAttribute("shoppingCart");
			if (shoppingCart == null) {
				shoppingCart = new ArrayList<Drug>();
			}

			Integer errorIdInCart = checkDrugInCart(shoppingCart, drugId);
			if (errorIdInCart != null) {
				session.setAttribute("errorIdInCart", errorIdInCart);
				response.sendRedirect(previousURI);
				return;
			}

			Drug drug = goodService.getDrugById(drugId, requestLocale);
			drug.setQuantity(1.00f);
			shoppingCart.add(drug);

			session.setAttribute("shoppingCart", shoppingCart);

		} else {
			List<Drug> drugList = cartService.getDrugsFromCart(userId,
					requestLocale);

			Integer errorIdInCart = checkDrugInCart(drugList, drugId);
			if (errorIdInCart != null) {
				session.setAttribute("errorIdInCart", errorIdInCart);
				response.sendRedirect(previousURI);
				return;
			}
			cartService.addDrugToCart(drugId, userId);
		}
		response.sendRedirect(previousURI);
	}

	private Integer checkDrugInCart(List<Drug> drugList, Integer drugId) {
		for (int i = 0; i < drugList.size(); i++) {
			if (drugId.equals(drugList.get(i).getId())) {
				return drugId;
			}
		}
		return null;
	}

	private boolean isUserAuthentificated(Integer userId) {
		if (userId == null) {
			return false;
		}
		return true;
	}
}
