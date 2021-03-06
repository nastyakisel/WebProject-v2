package com.finalproject.onlineapteka.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.OrderDetailService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class GetOrderDetailsCommand extends Command {

	OrderDetailService orderDetailService = (OrderDetailService) ServiceFactory
			.getInstance().getOrderDetailService();
	
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {

		HttpSession session = request.getSession();
		String customOrderId = request.getParameter("orderId");
		
		List<Drug> drugsInOrder = orderDetailService.getDrugsFromOrderDetail(Integer
				.parseInt(customOrderId), requestLocale);

		session.setAttribute("drugsInCustomOrder", drugsInOrder);
		session.setAttribute("customOrderId", customOrderId);
		response.sendRedirect("orderDetails.jsp");
	}
}
