package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.dao.CategoryDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class CategoryDaoImpl implements CategoryDao {

	private static final String SELECT_ALL_CATEGORIES = "SELECT* FROM category";
	
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class
			.getName());
	@Override
	public List<Category> loadAllCategories() throws DAOException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Category> categoryList = new ArrayList<Category>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_ALL_CATEGORIES);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Category category = new Category();
				category.setId(resultSet.getInt(1));
				category.setCategoryName(resultSet.getString(2));
				categoryList.add(category);
			}
		} catch (InterruptedException | SQLException e) {
			logger.error("loadAllCategoriesExeption", e);
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return categoryList;
	}
}
