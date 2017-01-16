package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class AddDrugCommand implements Command{
	private static final Logger LOGGER = LogManager.getLogger(EditCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		
		List<String> strList = new ArrayList<String>();
		
		String goodName = request.getParameter("good_name");
		strList.add(goodName);
		String description = request.getParameter("good_descr");
		strList.add(description);
		String goodDosage = request.getParameter("dosage");
		strList.add(goodDosage);
		String instruction = request.getParameter("instruction");
		strList.add(instruction);
		String goodPrice = request.getParameter("price");
		strList.add(goodPrice);
		String goodQuantity = request.getParameter("quantity");
		strList.add(goodQuantity);
		String goodRecipe =  request.getParameter("recipe");
		strList.add(goodRecipe);
		String imagePath = request.getParameter("image");
		strList.add(imagePath);
		String goodCategoryId = request.getParameter("cat_id");
		strList.add(goodCategoryId);
		
		List<ErrorBean> errors = validateInput(strList);
		
		if (!errors.isEmpty()) {
			dispatcher = request.getRequestDispatcher("addGood.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
		
		Drug drug = new Drug();
		drug.setDrugName(goodName);
		drug.setDescription(description);
		drug.setDosage(goodDosage);
		drug.setInstruction(instruction);
		drug.setPrice(BigDecimal.valueOf(Double.valueOf(goodPrice)));
		drug.setQuantity(Float.valueOf(goodQuantity));
		drug.setNeedRecipe(Integer.parseInt(goodRecipe));
		drug.setImagePath(imagePath);
		drug.setCategoryId(Integer.parseInt(goodCategoryId));
		
		DrugService service = (DrugService) ServiceFactory.getInstance().getGoodsService();
			
			try {
				service.addDrug(drug);
			} catch (ServiceException e) {
				LOGGER.error("Failed to add the good", e);
			}
			
		String previousURL = request.getParameter("previousURI");
		response.sendRedirect(previousURL);
	}
	}
	private List<ErrorBean> validateInput(List<String> inputList) {
		List<ErrorBean> errors = new ArrayList<ErrorBean>();
		for (int i = 0; i < inputList.size(); i++) {
			if (inputList.get(i).isEmpty()) {
				ErrorBean emptyInput = new ErrorBean(inputList.get(i));
				errors.add(emptyInput);
			}
		}

		return errors;
	}
}
