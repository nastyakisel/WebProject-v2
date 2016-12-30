package com.finalproject.onlineapteka.command.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Category;import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.exception.ServiceException;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class EditGoodCommand implements Command {
	private static final Logger logger = LogManager.getLogger(EditCommand.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		
		String goodId = request.getParameter("good_id");
		String goodName = request.getParameter("good_name");
		String description = request.getParameter("good_descr");
		String goodDosage = request.getParameter("dosage");
		String instruction = request.getParameter("instruction");
		String goodPrice = request.getParameter("price");
		String goodQuantity = request.getParameter("quantity");
		String goodRecipe =  request.getParameter("recipe");
		String imagePath = request.getParameter("image");
		String goodCategoryId = request.getParameter("cat_id");
			
		List<ErrorBean> errors = new ArrayList<ErrorBean>();
		
		if (goodName.isEmpty()) {
			ErrorBean emptyName = new ErrorBean("addGood.emptyName.error");
			errors.add(emptyName);
		}
		
		if (description.isEmpty()) {
			ErrorBean emptyDescription = new ErrorBean("addGood.emptyDescription.error");
			errors.add(emptyDescription);
		}
		
		if (goodDosage.isEmpty()) {
			ErrorBean emptyDosage = new ErrorBean("addGood.emptyDosage.error");
			errors.add(emptyDosage);
		}
		
		if (instruction.isEmpty()) {
			ErrorBean emptyInstruction = new ErrorBean("addGood.emptyInstruction.error");
			errors.add(emptyInstruction);
		}
		if (goodPrice.isEmpty()) {
			ErrorBean emptyGoodPrice = new ErrorBean("addGood.emptyGoodPrice.error");
			errors.add(emptyGoodPrice);
		}
		
		if (goodQuantity.isEmpty()) {
			ErrorBean emptyGoodQuantity = new ErrorBean("addGood.emptyGoodQuantity.error");
			errors.add(emptyGoodQuantity);
		}
		if (goodRecipe.isEmpty()) {
			ErrorBean emptyGoodRecipe = new ErrorBean("addGood.emptyGoodRecipe.error");
			errors.add(emptyGoodRecipe);
		}
		
		if (imagePath.isEmpty()) {
			ErrorBean emptyPassword = new ErrorBean("addGood.emptyGoodRecipe.error");
			errors.add(emptyPassword);
		}
		if (goodCategoryId.isEmpty()) {
			ErrorBean emptyGoodCategoryId = new ErrorBean("addGood.emptyGoodCategoryId.error");
			errors.add(emptyGoodCategoryId);
		}
		
		if (!errors.isEmpty()) {
			dispatcher = request.getRequestDispatcher("addGood.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
		
		Drug drug = new Drug();
		drug.setId(Integer.parseInt(goodId));
		drug.setDrugName(goodName);
		drug.setDescription(description);
		drug.setDosage(goodDosage);
		drug.setInstruction(instruction);
		drug.setPrice(BigDecimal.valueOf(Double.valueOf(goodPrice)));
		drug.setQuantity(Float.valueOf(goodQuantity));
		drug.setNeedRecipe(Integer.parseInt(goodRecipe));
		drug.setImagePath(imagePath);
		drug.setCategoryId(Integer.parseInt(goodCategoryId));
		
		GoodsService service = (GoodsService) ServiceFactory.getInstance().getGoodsService();
			
			try {
				service.editGood(drug);
			} catch (ServiceException e) {
				logger.error("Failed adding the good", e);
			}
			
		String previousURL = request.getParameter("previousURI");
		response.sendRedirect(previousURL);
	}
	}
}
