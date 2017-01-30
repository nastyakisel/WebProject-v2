package com.finalproject.onlineapteka.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;
import com.finalproject.onlineapteka.utils.ValidationUtils;

public class LoginCommand extends Command {

	UserService userService = ServiceFactory.getInstance().getUserService();
	CartService cartService = ServiceFactory.getInstance().getCartService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		String prevURL = request.getHeader("referer");
		
		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");
		List<String> inputList = new ArrayList<>();
		
		List<ErrorBean> errorList = ValidationUtils.validateInput(inputList);
		
		if (!errorList.isEmpty()) {
			session.setAttribute("has_errors", errorList);
			response.sendRedirect(prevURL);
			return;
		}
		
		User receivedUser = userService.getUser(userName.toCharArray(), password.toCharArray());

		ErrorBean userError = validateUser(receivedUser);

		session.setAttribute("has_error", null);
		if (userError != null) {
			session.setAttribute("has_error", userError);
			session.setAttribute("current_user", userName);
			response.sendRedirect(prevURL);
			return;
		}

		session.setAttribute("userId", receivedUser.getId());

		List<Drug> shoppingCart = (List<Drug>) session  
				.getAttribute("shoppingCart");

		if (shoppingCart != null) {
			
			List<Drug> drugList = cartService.getDrugsFromCart(receivedUser.getId(),
					requestLocale);

			metka: for (int i = 0; i < shoppingCart.size(); i++) {
				for (int j = 0; j < drugList.size(); j++) {
					if (shoppingCart.get(i).getId() == drugList.get(j).getId()) {
						continue metka;
					}
				}

				cartService.addDrugToCart(shoppingCart.get(i).getId(),
						receivedUser.getId());

			}
		}
		Command getAllGoods = new GetAllDrugsSessionCommand();
		getAllGoods.execute(request, response);
		
		Integer roleId = receivedUser.getRoleId();

		if (roleId == 3) { // doctor
			session.setAttribute("doctorUser", receivedUser);
			Command doctorCommand = new DoctorCommand();
			doctorCommand.execute(request, response);

		} else {
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
