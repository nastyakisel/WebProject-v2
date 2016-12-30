package com.finalproject.onlineapteka.dao.factories;

import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.UserDao;

public interface DAOFactory {
	UserDao getUserDao();
	GoodsDao getGoodsDao();
	CategoryDao getCategoryDao();
}
