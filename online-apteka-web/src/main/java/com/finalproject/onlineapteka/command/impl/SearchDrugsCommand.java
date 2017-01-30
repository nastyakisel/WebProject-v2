package com.finalproject.onlineapteka.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class SearchDrugsCommand extends Command {

	DrugService drugService = (DrugService) ServiceFactory.getInstance()
			.getDrugService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		String drugName = request.getParameter("search");
		
		if(drugName.isEmpty()) {
			session.setAttribute("drug_List", null);
			response.sendRedirect("searchResults.jsp");
			return;
		}
		session.setAttribute("drug_List", null);
		
		List<Drug> drugList = drugService.getDrugsByName(drugName, requestLocale);
		
		session.setAttribute("drug_List", drugList);
		
		response.sendRedirect("searchResults.jsp");	
	}
}
