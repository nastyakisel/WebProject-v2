package com.finalproject.onlineapteka.command.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.finalproject.onlineapteka.bean.CustomOrder;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.ErrorBean;
import com.finalproject.onlineapteka.bean.OrderDetail;
import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.OrderDetailService;
import com.finalproject.onlineapteka.service.OrderService;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.factory.ServiceFactory;

public class MakeOrderCommand extends Command {

	public void handle(HttpServletRequest request,
			HttpServletResponse response, String requestLocale)
			throws Exception {
		OrderService orderService = (OrderService) ServiceFactory.getInstance()
				.getOrderService();
		OrderDetailService orderDetailService = (OrderDetailService) ServiceFactory
				.getInstance().getOrderDetailService();
		CartService cartService = (CartService) ServiceFactory.getInstance()
				.getCartService();
		DrugService drugService = (DrugService) ServiceFactory.getInstance()
				.getDrugService();
		RecipeService recipeService = ServiceFactory.getInstance()
				.getRecipeService();

		HttpSession session = request.getSession();
		String prevURL = request.getHeader("referer");

		Integer userId = (Integer) session.getAttribute("userId");
		List<Drug> shoppingCart = (List<Drug>) session
				.getAttribute("shoppingCart");
		List<ErrorBean> errors = new ArrayList<>();
		session.setAttribute("has_errors", null);
		String totalPrice = request.getParameter("sumTotal");

		CustomOrder customOrder = new CustomOrder();
		Date orderDate = new Date(System.currentTimeMillis());

		customOrder.setOrderDate(orderDate);
		customOrder
				.setTotalPrice(BigDecimal.valueOf(Double.valueOf(totalPrice)));
		customOrder.setUserId(userId);
		customOrder.setOrderStatus(0);
		Integer customOrderId = 0;

		customOrderId = orderService.addOrder(customOrder);

		for (int i = 0; i < shoppingCart.size(); i++) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setDrugId(shoppingCart.get(i).getId());
			Float quantity = shoppingCart.get(i).getQuantity();
			Drug drug = null;

			drug = drugService.getDrugById(shoppingCart.get(i).getId(),
					requestLocale);

			if (quantity > drug.getQuantity()) {
				ErrorBean errorBean = new ErrorBean("Incorrect quantity");
				errors.add(errorBean);
				session.setAttribute("has_errors", errors);
				session.setAttribute("wrongId", shoppingCart.get(i).getId());
				response.sendRedirect(prevURL);
				return;
			}
			orderDetail.setQuantity(quantity);
			BigDecimal bigDecimalQuantity = BigDecimal.valueOf(Double
					.valueOf(shoppingCart.get(i).getQuantity()));
			BigDecimal price = shoppingCart.get(i).getPrice();
			BigDecimal totalPriceOfGood = price.multiply(bigDecimalQuantity);
			orderDetail.setTotalPriceOfGood(totalPriceOfGood);
			orderDetail.setCustomOrderId(customOrderId);

			orderDetailService.addOrderDetail(orderDetail);

			drug.setQuantity(drug.getQuantity() - quantity);

			drugService.editDrug(drug);

		}// for
		session.setAttribute("shoppingCart", null);

		cartService.removeCart(userId);

		Integer recipeId = (Integer) session.getAttribute("recipeId");
		if (recipeId != null) {
			Date endDate = new Date(0);

			recipeService.updateRecipe(endDate, recipeId);
		}

		List<Drug> drugsInOrder = new ArrayList<>();

		drugsInOrder = orderDetailService
				.getDrugsFromOrderDetail(customOrderId, requestLocale);

		session.setAttribute("drugsInOrder", drugsInOrder);
		session.setAttribute("customOrderId", customOrderId);
		session.setAttribute("totalPrice", totalPrice);
		response.sendRedirect("orderConfirmation.jsp");
	} 
}
