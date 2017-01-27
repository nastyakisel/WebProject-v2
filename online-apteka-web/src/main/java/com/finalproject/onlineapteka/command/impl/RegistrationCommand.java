package com.finalproject.onlineapteka.command.impl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;
import com.finalproject.onlineapteka.utils.ValidationUtils;

public class RegistrationCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		String prevURL = request.getHeader("referer");
		List<String> inputList = new ArrayList<>();
		String firstName = request.getParameter("first_name");
		inputList.add(firstName);
		String secondName = request.getParameter("second_name");
		inputList.add(secondName);
		String password = request.getParameter("password");
		inputList.add(password);
		String email = request.getParameter("email");
		inputList.add(email);

		List<ErrorBean> errors = ValidationUtils.validateInput(inputList);
		session.setAttribute("empty_errors", null);

		if (!errors.isEmpty()) {
			session.setAttribute("empty_errors", errors);
			response.sendRedirect(prevURL);
			return;
		}
		
		User user = new User();
		user.setUserName(email);
		user.setPassword(password);
		user.setRoleId(1);
		user.setFirstName(firstName);
		user.setSecondName(secondName);

		Integer userId = null;
		UserService addService = ServiceFactory.getInstance().getUserService();

		userId = addService.addUser(user);

		session.setAttribute("reg_error", null);
		if (userId == 0) {
			ErrorBean registrationError = new ErrorBean(
					"registrationPage.existUserError");
			session.setAttribute("reg_error", registrationError);
			response.sendRedirect(prevURL);
			return;

		}
		Command getAllGoods = new GetAllDrugsSessionCommand();
		getAllGoods.execute(request, response);
		session.setAttribute("userId", userId);
		response.sendRedirect("start.jsp");
	}
	
}
