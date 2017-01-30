package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.RecipeDetailService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class CheckCartCommand2 extends Command {

	CartService cartService = ServiceFactory.getInstance().getCartService();
	UserService userService = ServiceFactory.getInstance().getUserService();
	RecipeDetailService recipeDetailService = ServiceFactory.getInstance()
			.getRecipeDetailService();
	DrugService drugService = (DrugService) ServiceFactory
			.getInstance().getDrugService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();

		session.setAttribute("has_errors", null);
		String count = request.getParameter("count");
		String delete = request.getParameter("delete");
		String buy = request.getParameter("buy");
		
		if (count != null) {
			doCount(request, response, requestLocale, session);
			response.sendRedirect("cart.jsp");
		} 

		if (delete != null) {
			doDelete(request, response, requestLocale, session);
			response.sendRedirect("cart.jsp");
		} 

		if (buy != null) {
			doBuy(request, response, requestLocale, session);
		} 
	}
	
	private boolean isUserAuthentificated(Integer userId) {
		if (userId == null) {
			return false;
		}
		return true;
	}
	
	public void doCount(HttpServletRequest request,
			HttpServletResponse response, String requestLocale, HttpSession session){
		
		Integer userId = (Integer) session.getAttribute("userId");
		String[] drugId = request.getParameterValues("drugId");
		String[] drugQuantity = request.getParameterValues("quantity");
		List<Drug> shoppingCart = (List<Drug>) session
				.getAttribute("shoppingCart");
		if (isUserAuthentificated(userId)) {
			for (int k = 0; k < drugId.length; k++) {
				Cart cart = new Cart();
				cart.setQuantity(Float.valueOf(drugQuantity[k]));
				cart.setDrugId(Integer.parseInt(drugId[k]));
				cart.setUserId(userId);
				try {
					cartService.changeCart(cart);
				} catch (ServiceException e) {
				}

			} // for

			try {
				shoppingCart = cartService.getDrugsFromCart(userId,
						requestLocale);
			} catch (ServiceException e) {
				
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
	}
	public void doDelete(HttpServletRequest request,
			HttpServletResponse response, String requestLocale, HttpSession session) {
		
		String[] drugsToDelete = request.getParameterValues("checkbox");
		String prevURL = request.getHeader("referer");
		Integer userId = (Integer) session.getAttribute("userId");
		List<Drug> shoppingCart = (List<Drug>) session
				.getAttribute("shoppingCart");
		if (drugsToDelete == null) {
			try {
				response.sendRedirect(prevURL);
			} catch (IOException e) {
			}
			return;
		}

		if (!isUserAuthentificated(userId)) {

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
					shoppingCart = cartService.getDrugsFromCart(userId,
							requestLocale);
				} catch (NumberFormatException | ServiceException e) {	
				}
			}
			session.setAttribute("shoppingCart", shoppingCart);
		} // else
		
	}
	public void doBuy(HttpServletRequest request,
			HttpServletResponse response, String requestLocale, HttpSession session) {
		List<Drug> shoppingCart = (List<Drug>) session
				.getAttribute("shoppingCart");
		String prevURL = request.getHeader("referer");
		Integer userId = (Integer) session.getAttribute("userId");
		List<ErrorBean> errors = new ArrayList<>();
		for (int i = 0; i < shoppingCart.size(); i++) { 
			Drug drug = null;
			try {
				drug = drugService.getDrugById(shoppingCart.get(i).getId(),
						requestLocale);
			} catch (ServiceException e) {
				
			}

			if (shoppingCart.get(i).getQuantity() > drug.getQuantity()) { 
				ErrorBean errorBean = new ErrorBean("incorrect quantity");
				errors.add(errorBean);
				session.setAttribute("has_errors", errors);
				session.setAttribute("wrongId", shoppingCart.get(i).getId());
				try {
					response.sendRedirect(prevURL);
				} catch (IOException e) {
					
				}
				return;
			}
		}

		if (isUserAuthentificated(userId)) {
			User user = null;
			try {
				user = userService.getUserById(userId);
			} catch (ServiceException e) {
				
			}

			// List<Integer> idList = null;

			for (int i = 0; i < shoppingCart.size(); i++) { // проверяем,есть
															// ли рецепт

				if (shoppingCart.get(i).getNeedRecipe() == 1) {
					List<Integer> recipeIdList = null;
					Date endDate = new Date(System.currentTimeMillis());

					try {
						recipeIdList = recipeDetailService
								.getDrugsFromRecipeByUser(userId, endDate,
										shoppingCart.get(i).getId(),
										shoppingCart.get(i).getQuantity());
					} catch (ServiceException e) {
						
					}

					if (recipeIdList.isEmpty()) {
						ErrorBean errorBean = new ErrorBean("no recipe");
						errors.add(errorBean);
						session.setAttribute("has_errors", errors);
						session.setAttribute("wrongId", shoppingCart.get(i)
								.getId());
						try {
							response.sendRedirect(prevURL);
						} catch (IOException e) {
							
						}
						return;
					}
					session.setAttribute("recipeId", recipeIdList.get(0));
				}
			}

			session.setAttribute("user", user);
			try {
				response.sendRedirect("invoice.jsp");
			} catch (IOException e) {
				
			}
		} else {
			try {
				response.sendRedirect(prevURL);
			} catch (IOException e) {
				
			}
		}
	}
}
