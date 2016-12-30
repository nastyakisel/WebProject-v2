package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class LoginCommand implements Command {
	private static final Logger logger = LogManager.getLogger(EditCommand.class
			.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("user_login");
		String password = request.getParameter("user_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);

		HttpSession session = request.getSession();

		List<ErrorBean> errors = new ArrayList<ErrorBean>();

		UserService service = ServiceFactory.getInstance().getUserService();
		User receivedUser = null;

		try {
			receivedUser = service.getUser(userName, password);
		} catch (ServiceException e) {
			logger.error("Failed receiving the user", e);
		}

		if (receivedUser == null) {
			ErrorBean errorName = new ErrorBean("loginPage.errorUser");
			errors.add(errorName);
		}

		if (!errors.isEmpty()) {
			session.setAttribute("has_errors", errors);
			session.setAttribute("current_user", user);
			response.sendRedirect("login.jsp");

			return;
		}

		session.setAttribute("userId", receivedUser.getId());

		List<Drug> drugList = null;
		List<Category> categoryList = null;
		GoodsService goodsService = (GoodsService) ServiceFactory.getInstance()
				.getGoodsService();
		CategoryService categoryService = ServiceFactory.getInstance()
				.getCategoryService();

		try {
			drugList = goodsService.getAllGoods();
			categoryList = categoryService.getAllCategories();
		} catch (ServiceException e) {
			logger.error("Failed receiving the good", e);

		}
		
		String session_Id = session.getId();
		session.setAttribute("session_Id", session_Id);
		session.setAttribute("drug_List", drugList);
		session.setAttribute("category_List", categoryList);

		Integer roleId = receivedUser.getRoleId();

		response.sendRedirect(getURI(roleId));

	}

	public String getURI(Integer role) {
		Map<String, String> roleMap = new HashMap<String, String>();
		roleMap.put("1", "start.jsp");
		roleMap.put("2", "administratorPage.jsp");
		roleMap.put("3", "administratorPage.jsp");

		String uri = roleMap.get(role.toString());
		return uri;

	}
}
