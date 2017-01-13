package com.finalproject.onlineapteka.service.impl;

import java.util.List;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.CategoryService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class CategoryServiceImpl implements CategoryService{
	@Override
	public List<Category> getAllCategories() throws ServiceException {
		CategoryDao categoryDao = DAOFactoryImpl.getInstance().getCategoryDao();
		List<Category> categoryList = null;
		try {
			categoryList = categoryDao.loadAllCategories();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return categoryList;
	}
	@Override
	public List<Category> getAllCategories(String locale) throws ServiceException {
		CategoryDao categoryDao = DAOFactoryImpl.getInstance().getCategoryDao();
		List<Category> categoryList = null;
		try {
			categoryList = categoryDao.loadAllCategories(locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return categoryList;
	}
}
