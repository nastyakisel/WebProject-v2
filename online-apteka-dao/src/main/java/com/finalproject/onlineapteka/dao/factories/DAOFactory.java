package com.finalproject.onlineapteka.dao.factories;

import com.finalproject.onlineapteka.dao.CartDao;
import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.OrderDao;
import com.finalproject.onlineapteka.dao.OrderDetailDao;
import com.finalproject.onlineapteka.dao.RecipeDao;
import com.finalproject.onlineapteka.dao.RecipeDetailDao;
import com.finalproject.onlineapteka.dao.UserDao;

public interface DAOFactory {
	UserDao getUserDao();
	DrugDao getGrugDao();
	CategoryDao getCategoryDao();
	CartDao getCartDao();
	OrderDao getOrderDao();
	OrderDetailDao getOrderDetailDao();
	RecipeDao getRecipeDao();
	RecipeDetailDao getRecipeDetailDao();
}
