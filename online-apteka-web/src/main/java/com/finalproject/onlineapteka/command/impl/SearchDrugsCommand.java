package com.finalproject.onlineapteka.command.impl;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class SearchDrugsCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		String drugName = request.getParameter("search");
		List<Drug> drugList = null;
		DrugService drugService = (DrugService) ServiceFactory.getInstance()
				.getDrugService();

		drugList = drugService.getDrugsByName(drugName);

		request.setAttribute("drug_List", drugList);

		Command categoryCommand = new GetCategoriesCommand();
		categoryCommand.execute(request, response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("start.jsp");
		dispatcher.forward(request, response);
	}
}
