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

import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class RegistrationCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class
			.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		String firstName = request.getParameter("first_name");
		String secondName = request.getParameter("second_name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		User user = new User();
		user.setUserName(email);
		user.setPassword(password);
		user.setRoleId(1);
		user.setFirstName(firstName);
		user.setSecondName(secondName);

		List<ErrorBean> errors = new ArrayList<ErrorBean>();

		Integer id = null;
		UserService addService = ServiceFactory.getInstance().getUserService();
		try {
			id = addService.addUser(user);
		} catch (ServiceException e) {
			LOGGER.error("Failed adding the user", e);
		}
		if (id == 0) {
			ErrorBean errorName = new ErrorBean(
					"registrationPage.existUserError");
			errors.add(errorName);
		}

		HttpSession session = request.getSession();
		if (!errors.isEmpty()) {
			session.setAttribute("has_errors", errors);
			response.sendRedirect("registration.jsp");
			return;
		}
		
		session.setAttribute("session_user", user);
		response.sendRedirect("start.jsp");
	}
	
}
