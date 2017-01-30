package com.finalproject.onlineapteka.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class EditCommand extends Command {

	DrugService drugService = (DrugService) ServiceFactory.getInstance()
			.getDrugService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		Integer drugId = getParameterFromRequestAsInteger("drugId", request);
		Drug recievedDrug = drugService.getDrugById(drugId, requestLocale);

		String previousURI = request.getHeader("referer");
		request.setAttribute("previousURI", previousURI);
		request.setAttribute("drug", recievedDrug);
		forward("edit.jsp", request, response);
	}
}
