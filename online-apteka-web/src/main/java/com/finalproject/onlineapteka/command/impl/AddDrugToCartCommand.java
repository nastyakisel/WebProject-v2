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
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class AddDrugToCartCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(AddDrugToCartCommand.class.getName());
	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DrugService goodService = (DrugService) ServiceFactory.getInstance()
 				.getGoodsService();
		CartService cartService = ServiceFactory.getInstance()
 				.getCartService();
		Integer drugId = Integer.parseInt(request.getParameter("drugId"));
		 
 		HttpSession session = request.getSession();
 		String previousURI = request.getHeader("referer");
 		Integer userId = (Integer) session.getAttribute("userId");
 		String requestLocale = (String) session.getAttribute("requestLocale");
 		if (requestLocale == null) {
			requestLocale = "ru";
		}
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
			try {
				drug = goodService.getDrugById(drugId, requestLocale);
				drug.setQuantity(1.00f);
 			} catch (ServiceException e) {
 				LOGGER.error("Failed receiving the drug", e);
 			}
 			shoppingCart.add(drug);
 			
 			session.setAttribute("shoppingCart", shoppingCart);
 
 		}
 		else {
 			List<Drug> drugList = null;
			try {
				drugList = cartService.getDrugsFromCart(userId, requestLocale);
			} catch (ServiceException e1) {
				LOGGER.error("Failed receiving the drug", e1);
			}
			Integer errorId = checkDrugInCart(drugList, userId, drugId);
			if (errorId != null) {
 				session.setAttribute("has_errors", errorId);
 				response.sendRedirect(previousURI);
 				return;
 			}
 			try {
 				cartService.addDrugToCart(drugId, userId);
 			} catch (ServiceException e) {
 				LOGGER.error("Failed adding to cart", e);
 			}
 			
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

 	