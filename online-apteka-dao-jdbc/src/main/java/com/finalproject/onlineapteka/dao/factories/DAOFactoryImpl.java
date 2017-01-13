package com.finalproject.onlineapteka.dao.factories;

import com.finalproject.onlineapteka.dao.CartDao;
import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.OrderDao;
import com.finalproject.onlineapteka.dao.OrderDetailDao;
import com.finalproject.onlineapteka.dao.RecipeDao;
import com.finalproject.onlineapteka.dao.RecipeDetailDao;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.jdbc.impl.CartDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.CategoryDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.DrugDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.OrderDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.OrderDetailDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.RecipeDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.RecipeDetailDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.UserDaoImpl;

public class DAOFactoryImpl implements DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactoryImpl();

	private UserDao userDao = new UserDaoImpl();
	private DrugDao drugDao = new DrugDaoImpl();
	private CategoryDao categoryDao = new CategoryDaoImpl();
	private CartDao cartDao = new CartDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
	private RecipeDao recipeDao = new RecipeDaoImpl();
	private RecipeDetailDao recipeDetailDao = new RecipeDetailDaoImpl();

	private DAOFactoryImpl() {
	}
	
	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public UserDao getUserDao() {
		return userDao;
	}

	@Override
	public DrugDao getGrugDao() {
		return drugDao;
	}

	@Override
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}
	
	@Override
	public CartDao getCartDao() {
		return cartDao;
	}
	
	@Override
	public OrderDao getOrderDao() {
		return orderDao;
	}
	
	@Override
	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}
	
	@Override
	public RecipeDao getRecipeDao() {
		return recipeDao;
	}
	
	@Override
	public RecipeDetailDao getRecipeDetailDao() {
		return recipeDetailDao;
	}
}
