package com.finalproject.onlineapteka.command.impl;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class EditCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		Integer goodId = Integer.parseInt(request.getParameter("drugId"));
		Drug recievedDrug = null;
		DrugService service = (DrugService) ServiceFactory.getInstance()
				.getDrugService();

		recievedDrug = service.getDrugById(goodId, requestLocale);

		String previousURI = request.getHeader("referer");
		request.setAttribute("previousURI", previousURI);
		request.setAttribute("drug", recievedDrug);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		dispatcher.forward(request, response);
	}
}
