package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
	private static final Logger logger = LogManager.getLogger(EditCommand.class
			.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setRoleId(1);

		List<ErrorBean> errors = new ArrayList<ErrorBean>();

		Integer id = null;
		UserService addService = ServiceFactory.getInstance().getUserService();
		try {
			id = addService.addUser(user);
		} catch (ServiceException e) {
			logger.error("Failed adding the user", e);
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
		response.sendRedirect("welcome.jsp");
	}
}
