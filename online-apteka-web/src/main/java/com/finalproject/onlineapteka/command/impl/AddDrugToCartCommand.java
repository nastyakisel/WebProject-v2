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
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class AddDrugToCartCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(AddDrugToCartCommand.class.getName());
	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DrugService goodService = (DrugService) ServiceFactory.getInstance()
 				.getGoodsService();
		Integer drugId = Integer.parseInt(request.getParameter("drugId"));
		 
 		HttpSession session = request.getSession();
 		Integer userId = (Integer) session.getAttribute("userId");
 		List<Drug> shoppingCart = (List<Drug>) session
 								.getAttribute("shoppingCart");
 		if (userId == null) {
 			if (shoppingCart == null) {
 				shoppingCart = new ArrayList<Drug>();
 			}
 			Drug drug = null;
 			try {
 				drug = goodService.getDrugById(drugId);
				drug.setQuantity(1.00f);
 			} catch (ServiceException e) {
 				LOGGER.error("Failed adding to cart", e);
 			}
 			shoppingCart.add(drug);
 			String session_Id = session.getId();
 			session.setAttribute("session_Id", session_Id);
 			session.setAttribute("shoppingCart", shoppingCart);
 
 		}
 		else {
 			try {
 				goodService.addDrugToCart(drugId, userId);
 			} catch (ServiceException e) {
 				LOGGER.error("Failed adding to cart", e);
 			}
 			if (shoppingCart != null) {
 				for (int i = 0; i < shoppingCart.size(); i++) {
 					try {
 						goodService.addDrugToCart(shoppingCart.get(i).getId(), userId);
 					} catch (ServiceException e) {
 						LOGGER.error("Failed adding to cart", e);
 					}
 				}
 				shoppingCart = null;
 			}
 			response.sendRedirect("start.jsp");
 			/*if (shoppingCart == null) {
 				shoppingCart = new ArrayList<Drug>();
 			}
 			try {
 				shoppingCart = goodService.getDrugsFromCart(userId);
 			} catch (ServiceException e) {
 				logger.error("Failed receiving from cart", e);
 			}
 			String session_Id = session.getId();
 			session.setAttribute("session_Id", session_Id);
 			session.setAttribute("shoppingCart", shoppingCart);
 		}*/
		}
	}
}	

 	