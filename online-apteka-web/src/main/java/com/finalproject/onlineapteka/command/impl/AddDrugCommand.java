package com.finalproject.onlineapteka.command.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class AddDrugCommand extends Command {
	
	public void handle(HttpServletRequest request, HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		String prevURI = request.getHeader("referer");
		List<String> inputList = new ArrayList<String>();

		String goodName = request.getParameter("good_name");
		session.setAttribute("goodName", goodName);
		inputList.add(goodName);

		String description = request.getParameter("good_descr");
		session.setAttribute("description", description);
		inputList.add(description);

		String goodDosage = request.getParameter("dosage");
		session.setAttribute("goodDosage", goodDosage);
		inputList.add(goodDosage);

		String instruction = request.getParameter("instruction");
		session.setAttribute("instruction", instruction);
		inputList.add(instruction);

		String goodPrice = request.getParameter("price");
		session.setAttribute("goodPrice", goodPrice);
		inputList.add(goodPrice);

		String goodQuantity = request.getParameter("quantity");
		session.setAttribute("goodQuantity", goodQuantity);
		inputList.add(goodQuantity);

		String goodRecipe = request.getParameter("recipe");
		session.setAttribute("goodRecipe", goodRecipe);
		inputList.add(goodRecipe);

		String imagePath = request.getParameter("image");
		session.setAttribute("imagePath", imagePath);
		inputList.add(imagePath);

		String goodCategoryId = request.getParameter("cat_id");
		session.setAttribute("goodCategoryId", goodCategoryId);
		inputList.add(goodCategoryId);

		List<ErrorBean> errors = validateInput(inputList);
		session.setAttribute("has_errors", null);

		if (!errors.isEmpty()) {
			session.setAttribute("has_errors", errors);
			response.sendRedirect(prevURI);
			return;
		}
		
		Drug drug = new Drug();
		drug.setDrugName(goodName);
		drug.setDescription(description);
		drug.setDosage(goodDosage);
		drug.setInstruction(instruction);
		if (!NumberUtils.isNumber(goodPrice)){
			ErrorBean notNumber = new ErrorBean("goodPrice.not.number");
			errors.add(notNumber);
			session.setAttribute("has_errors", errors);
			response.sendRedirect(prevURI);
			return;
		}
		drug.setPrice(BigDecimal.valueOf(Double.valueOf(goodPrice)));
		if (!NumberUtils.isNumber(goodQuantity)){
			ErrorBean notNumber = new ErrorBean("goodQuantity.not.number");
			errors.add(notNumber);
			session.setAttribute("has_errors", errors);
			response.sendRedirect(prevURI);
			return;
		}
		drug.setQuantity(Float.valueOf(goodQuantity));
		drug.setNeedRecipe(Integer.parseInt(goodRecipe));
		drug.setImagePath(imagePath);
		
		if (!NumberUtils.isNumber(goodCategoryId)){
			ErrorBean notNumber = new ErrorBean("goodCategoryId.not.number");
			errors.add(notNumber);
			session.setAttribute("has_errors", errors);
			response.sendRedirect(prevURI);
			return;
		}
		drug.setCategoryId(Integer.parseInt(goodCategoryId));

		DrugService service = (DrugService) ServiceFactory.getInstance()
				.getDrugService();

		service.addDrug(drug);
		
		String previousURL = request.getParameter("previousURI");
		response.sendRedirect(previousURL);
	}

	private List<ErrorBean> validateInput(List<String> inputList) {
		List<ErrorBean> errors = new ArrayList<ErrorBean>();
		for (int i = 0; i < inputList.size(); i++) {
			if (inputList.get(i).isEmpty()) {
				ErrorBean emptyInput = new ErrorBean("addGood.emptyField");
				errors.add(emptyInput);
			}
		}
		return errors;
	}
}
