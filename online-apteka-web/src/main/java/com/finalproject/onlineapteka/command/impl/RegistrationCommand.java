package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;

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

		HttpSession session = request.getSession();
		String prevURL = request.getHeader("referer");
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

		Integer userId = null;
		UserService addService = ServiceFactory.getInstance().getUserService();
		try {
			userId = addService.addUser(user);
		} catch (ServiceException e) {
			LOGGER.error("Failed adding the user", e);
		}
		
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
