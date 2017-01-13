package com.finalproject.onlineapteka.service.factory;

import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.OrderDetailService;
import com.finalproject.onlineapteka.service.OrderService;
import com.finalproject.onlineapteka.service.RecipeDetailService;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.impl.CartServiceImpl;
import com.finalproject.onlineapteka.service.impl.CategoryServiceImpl;
import com.finalproject.onlineapteka.service.impl.DrugServiceImpl;
import com.finalproject.onlineapteka.service.impl.OrderDetailServiceImpl;
import com.finalproject.onlineapteka.service.impl.OrderServiceImpl;
import com.finalproject.onlineapteka.service.impl.RecipeDetailServiceImpl;
import com.finalproject.onlineapteka.service.impl.RecipeServiceImpl;
import com.finalproject.onlineapteka.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private UserService userService = new UserServiceImpl();
	private DrugService drugService = new DrugServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private OrderDetailService orderDetailService = new OrderDetailServiceImpl();
	private RecipeService recipeService = new RecipeServiceImpl();
	private RecipeDetailService recipeDetailService = new RecipeDetailServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public DrugService getGoodsService() {
		return drugService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}
	
	public CartService getCartService() {
		return cartService;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
	
	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}
	
	public RecipeService getRecipeService() {
		return recipeService;
	}
	
	public RecipeDetailService getRecipeDetailService() {
		return recipeDetailService;
	}
}
