package com.finalproject.onlineapteka.dao.factories;

import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.jdbc.impl.CategoryDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.GoodsDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.UserDaoImpl;

public class DAOFactoryImpl implements DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactoryImpl();

	private UserDao userDao = new UserDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private CategoryDao categoryDao = new CategoryDaoImpl();

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
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	@Override
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}
}
