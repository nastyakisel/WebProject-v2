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

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		String drugName = request.getParameter("search");
		if(drugName.isEmpty()) {
			response.sendRedirect("start.jsp");
			return;
		}
		List<Drug> drugList = null;
		DrugService drugService = (DrugService) ServiceFactory.getInstance()
				.getDrugService();

		drugList = drugService.getDrugsByName(drugName, requestLocale);
		HttpSession session = request.getSession();
		session.setAttribute("drug_List", drugList);

		response.sendRedirect("start.jsp");	
	}
}
