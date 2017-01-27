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
	
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {
		DrugService goodService = (DrugService) ServiceFactory.getInstance()
 				.getDrugService();
		CartService cartService = ServiceFactory.getInstance()
 				.getCartService();
		Integer drugId = Integer.parseInt(request.getParameter("drugId"));
		 
 		HttpSession session = request.getSession();
 		String previousURI = request.getHeader("referer");
 		Integer userId = (Integer) session.getAttribute("userId");
 		
 		List<Drug> shoppingCart = (List<Drug>) session
 								.getAttribute("shoppingCart");
 		
 		if (userId == null) {
 			if (shoppingCart == null) {
 				shoppingCart = new ArrayList<Drug>();
 			}
 			
 			Integer errorId = checkDrugInCart(shoppingCart, userId, drugId);
 			if (errorId != null) {
 				session.setAttribute("has_errors", errorId);
 				response.sendRedirect(previousURI);
 				return;
 			}
 			
 			Drug drug = null;
			drug = goodService.getDrugById(drugId, requestLocale);
			drug.setQuantity(1.00f);
 			
 			shoppingCart.add(drug);
 			
 			session.setAttribute("shoppingCart", shoppingCart);
 
 		}
 		else {
 			List<Drug> drugList = null;
			
				drugList = cartService.getDrugsFromCart(userId, requestLocale);
			
			Integer errorId = checkDrugInCart(drugList, userId, drugId);
			if (errorId != null) {
 				session.setAttribute("has_errors", errorId);
 				response.sendRedirect(previousURI);
 				return;
 			}
 				cartService.addDrugToCart(drugId, userId);
		}
 		response.sendRedirect(previousURI);
	}
	private Integer checkDrugInCart(List<Drug> drugList, Integer userId, Integer drugId) {
		for (int i = 0; i < drugList.size(); i++) {
				if (drugId == drugList.get(i).getId()) {
					return drugId;
				}
			}
		return null;
	}
}	

 	