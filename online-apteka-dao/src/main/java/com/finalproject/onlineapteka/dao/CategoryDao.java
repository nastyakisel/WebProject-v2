package com.finalproject.onlineapteka.dao;

import java.util.List;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface CategoryDao {
	List<Category> loadAllCategories() throws DAOException;
}
