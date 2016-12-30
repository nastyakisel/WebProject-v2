package com.finalproject.onlineapteka.service.factory;

import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.impl.CategoryServiceImpl;
import com.finalproject.onlineapteka.service.impl.GoodsServiceImpl;
import com.finalproject.onlineapteka.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	UserService userService = new UserServiceImpl();
	GoodsService goodsService = new GoodsServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}
}
