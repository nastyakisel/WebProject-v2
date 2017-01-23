package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class LoginCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(LoginCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);

		HttpSession session = request.getSession();
		String requestLocale = (String) session.getAttribute("requestLocale");
		if (requestLocale == null) {
			requestLocale = "ru";
		}
		String prevURL = request.getHeader("referer");
		UserService userService = ServiceFactory.getInstance().getUserService();
		User receivedUser = null;

		try {
			receivedUser = userService.getUser(userName, password);
		} catch (ServiceException e) {
			LOGGER.error("Failed receiving the user", e);
		}

		ErrorBean userError = validateUser(receivedUser);

		session.setAttribute("has_error", null);
		if (userError != null) {
			session.setAttribute("has_error", userError);
			session.setAttribute("current_user", user);
			response.sendRedirect(prevURL);

			return;
		}

		session.setAttribute("userId", receivedUser.getId());

		Command getAllGoods = new GetAllDrugsSessionCommand();
		getAllGoods.execute(request, response);

		List<Drug> shoppingCart = (List<Drug>) session
				.getAttribute("shoppingCart");
		
		CartService cartService = ServiceFactory.getInstance()
 				.getCartService();
		if (shoppingCart != null) {
			List<Drug> drugList = null;
			try {
				drugList = cartService.getDrugsFromCart(receivedUser.getId(),
						requestLocale);
			} catch (ServiceException e1) {
				LOGGER.error("Failed receiving from cart", e1);
			}
			metka: for (int i = 0; i < shoppingCart.size(); i++) {
				for (int j = 0; j < drugList.size(); j++) {
					if (shoppingCart.get(i).getId() == drugList.get(j).getId()) {
						continue metka;
					}
				}
				try {
					cartService.addDrugToCart(shoppingCart.get(i).getId(),
							receivedUser.getId());
				} catch (ServiceException e) {
					LOGGER.error("Failed adding drug to cart", e);
				}
			}
		}
		Integer roleId = receivedUser.getRoleId();

		if (roleId == 3) { // doctor
			session.setAttribute("doctorUser", receivedUser);
			Command doctorCommand = new DoctorCommand();
			doctorCommand.execute(request, response);

		} 
		else {
			response.sendRedirect(getURI(roleId));
		}
	}

	public String getURI(Integer role) {
		Map<String, String> roleMap = new HashMap<String, String>();
		roleMap.put("1", "start.jsp");
		roleMap.put("2", "administratorPage.jsp");
		// roleMap.put("3", "doctorPage.jsp");

		String uri = roleMap.get(role.toString());
		return uri;

	}

	private ErrorBean validateUser(User user) {
		ErrorBean error = null;
		if (user == null) {
			error = new ErrorBean("loginPage.errorUser");
		}
		return error;
	}
}
