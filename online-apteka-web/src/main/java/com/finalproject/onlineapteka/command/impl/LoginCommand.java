package com.finalproject.onlineapteka.command.impl;

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

public class LoginCommand extends Command {
	
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {

		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);

		HttpSession session = request.getSession();
		
		String prevURL = request.getHeader("referer");
		UserService userService = ServiceFactory.getInstance().getUserService();
		User receivedUser = null;

		
			receivedUser = userService.getUser(userName, password);
		
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
			
				drugList = cartService.getDrugsFromCart(receivedUser.getId(),
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
