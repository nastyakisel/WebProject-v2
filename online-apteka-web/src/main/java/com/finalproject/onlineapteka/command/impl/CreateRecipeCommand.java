package com.finalproject.onlineapteka.command.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;

import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.bean.RecipeDetail;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.RecipeDetailService;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;
import com.finalproject.onlineapteka.utils.ValidationUtils;

public class CreateRecipeCommand extends Command {

	UserService userService = ServiceFactory.getInstance().getUserService();
	RecipeService recipeService = ServiceFactory.getInstance()
			.getRecipeService();
	RecipeDetailService recipeDetailService = ServiceFactory.getInstance()
			.getRecipeDetailService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		String prevURI = request.getHeader("referer");
		User doctor = (User) session.getAttribute("doctorUser");
		User selectedUser = (User) session.getAttribute("selectedUser");

		Integer recipeId = (Integer) session.getAttribute("recipeId");
		List<String> inputList = new ArrayList<String>();
		if (!isResipeCreated(recipeId)) {

			Integer userIdForRecipe = getParameterFromRequestAsInteger("selectedUser", request);

			selectedUser = userService.getUserById(userIdForRecipe);

			String beginDate = request.getParameter("begin_date");
			String endDate = request.getParameter("end_date");
			inputList.add(beginDate);
			inputList.add(endDate);
			List<ErrorBean> errors = ValidationUtils.validateInput(inputList);
			if (!errors.isEmpty()) {
				session.setAttribute("has_errors", errors);
				response.sendRedirect(prevURI);
				return;
			}

			Recipe recipe = new Recipe();
			recipe.setDoctorName(doctor.getFirstName() + " " + doctor.getSecondName());
			recipe.setBeginDate(Date.valueOf(beginDate));
			recipe.setEndDate(Date.valueOf(endDate));
			recipe.setUserId(userIdForRecipe);

			recipeId = recipeService.addRecipe(recipe);

		}
		String selectedDrugId = request.getParameter("selectedDrug");
		String dosage = request.getParameter("dosage");
		String quantity = request.getParameter("quantity");
		inputList.add(dosage);
		inputList.add(quantity);
		List<ErrorBean> errors = ValidationUtils.validateInput(inputList);

		if (!errors.isEmpty()) {
			session.setAttribute("has_errors", errors);
			response.sendRedirect(prevURI);
			return;
		}
		RecipeDetail recipeDetail = new RecipeDetail();
		recipeDetail.setDosage(dosage);
		if (!NumberUtils.isNumber(quantity)) {
			ErrorBean notNumber = new ErrorBean("goodQuantity.not.number");
			errors.add(notNumber);
			session.setAttribute("has_errors", errors);
			response.sendRedirect(prevURI);
			return;
		}

		recipeDetail.setQuantity(Float.valueOf(quantity));
		recipeDetail.setDrugId(Integer.parseInt(selectedDrugId));
		recipeDetail.setRecipeId(recipeId);

		recipeDetailService.addRecipeDetail(recipeDetail);

		session.setAttribute("recipeId", recipeId);
		session.setAttribute("selectedUser", selectedUser);
		session.setAttribute("previousURI", prevURI);
		response.sendRedirect("assignRecipe_2.jsp");

	}
	private boolean isResipeCreated(Integer recipeId) {
		if (recipeId == null) {
			return false;
		}
		return true;
	}
}
