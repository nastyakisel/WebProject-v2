package com.finalproject.onlineapteka.service;

import java.util.List;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface CategoryService {
	public List<Category> getAllCategories() throws ServiceException;
	List<Category> getAllCategories(String locale) throws ServiceException;
}
