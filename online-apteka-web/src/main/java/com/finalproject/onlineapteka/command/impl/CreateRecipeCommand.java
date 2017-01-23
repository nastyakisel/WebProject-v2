package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.bean.RecipeDetail;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeDetailService;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class CreateRecipeCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(CreateRecipeCommand.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService userService = ServiceFactory.getInstance().getUserService();
		RecipeService recipeService = ServiceFactory.getInstance()
				.getRecipeService();
		RecipeDetailService recipeDetailService = ServiceFactory.getInstance()
				.getRecipeDetailService();

		HttpSession session = request.getSession();
		String prevURI = request.getHeader("referer");
		Integer userId = (Integer) session.getAttribute("userId");
		String previousURI = request.getHeader("referer");
		User doctor = (User) session.getAttribute("doctorUser");
		User selectedUser =(User) session.getAttribute("selectedUser");
		
		Integer recipeId = (Integer) session.getAttribute("recipeId");
		List<String> inputList = new ArrayList<String>();
		if (recipeId == null) {

			Integer userIdForRecipe = Integer.parseInt(request
					.getParameter("selectedUser"));
			try {
				selectedUser = userService.getUserById(userIdForRecipe);
			} catch (ServiceException e) {
				LOGGER.error("Failed receiving the user", e);
			}
			String beginDate = request.getParameter("begin_date");
			String endDate = request.getParameter("end_date");
			inputList.add(beginDate);
			inputList.add(endDate);
			List<ErrorBean> errors = validateInput(inputList);
			if (!errors.isEmpty()) {
				session.setAttribute("has_errors", errors);
				response.sendRedirect(prevURI);
				return;
			}
			
			Recipe recipe = new Recipe();
			recipe.setDoctorName(doctor.getUserName());
			recipe.setBeginDate(Date.valueOf(beginDate));
			recipe.setEndDate(Date.valueOf(endDate));
			recipe.setUserId(userIdForRecipe);

			try {
				recipeId = recipeService.addRecipe(recipe);
			} catch (ServiceException e) {
				LOGGER.error("Failed saving the recipe", e);
			}
		}
		String selectedDrugId = request.getParameter("selectedDrug");
		String dosage = request.getParameter("dosage");
		String quantity = request.getParameter("quantity");
		inputList.add(dosage);
		inputList.add(quantity);
		List<ErrorBean> errors = validateInput(inputList);
		
		if (!errors.isEmpty()) {
			session.setAttribute("has_errors", errors);
			response.sendRedirect(prevURI);
			return;
		}		
		RecipeDetail recipeDetail = new RecipeDetail();
		recipeDetail.setDosage(dosage);
		recipeDetail.setQuantity(Float.valueOf(quantity));
		recipeDetail.setDrugId(Integer.parseInt(selectedDrugId));
		recipeDetail.setRecipeId(recipeId);

		try {
			recipeDetailService.addRecipeDetail(recipeDetail);
		} catch (ServiceException e) {
			LOGGER.error("Failed saving the recipeDetail", e);
		}

		session.setAttribute("recipeId", recipeId);
		session.setAttribute("selectedUser", selectedUser);
		session.setAttribute("previousURI", previousURI);
		response.sendRedirect("assignRecipe_2.jsp");

	}
	private List<ErrorBean> validateInput(List<String> inputList) {
		List<ErrorBean> errors = new ArrayList<ErrorBean>();
		for (int i = 0; i < inputList.size(); i++) {
			if (inputList.get(i).isEmpty()) {
				ErrorBean emptyInput = new ErrorBean();
				errors.add(emptyInput);
			}
		}

		return errors;
	}
}
