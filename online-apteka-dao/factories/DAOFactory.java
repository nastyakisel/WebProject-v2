package com.finalproject.onlineapteka.dao.factories;

import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.jdbc.impl.CategoryDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.GoodsDaoImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.UserDaoImpl;


public class DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private UserDao userDao = new UserDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private CategoryDao categoryDao = new CategoryDaoImpl();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return INSTANCE;
	}
	
	public UserDao getUserDao(){
		return userDao;
	}
	
	public GoodsDao getGoodsDao(){
		return goodsDao;
	}
	
	public CategoryDao getCategoryDao(){
		return categoryDao;
	}
}
