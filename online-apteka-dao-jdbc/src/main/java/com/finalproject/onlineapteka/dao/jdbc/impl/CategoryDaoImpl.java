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

public class CategoryDaoImpl extends BaseDao implements CategoryDao {

	private static final String SELECT_ALL_CATEGORIES = "SELECT* FROM category";
	
	private static final String SELECT_ALL_CATEGORIES_BY_LOCALE = "SELECT * FROM category_locale WHERE locale =?";
	
	
	private static final Logger LOGGER = LogManager.getLogger(CategoryDaoImpl.class
			.getName());
	@Override
	public List<Category> loadAllCategories() throws DAOException{
		
		List<Category> categoryList = new ArrayList<Category>();
		ResultSet resultSet = createResultSet(SELECT_ALL_CATEGORIES); 
		
		try {
			while (resultSet.next()) {

				Category category = new Category();
				category.setId(resultSet.getInt(1));
				category.setCategoryName(resultSet.getString(2));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			LOGGER.error("Error occurs while categories loading", e);
			throw new DAOException(e);
		} 
		return categoryList;
		
		/*Connection connection = null;
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
			LOGGER.error("Error occurs while categories loading", e);
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return categoryList;*/
	}
	@Override
	public List<Category> loadAllCategories(String locale) throws DAOException{
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Category> categoryList = new ArrayList<Category>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_ALL_CATEGORIES_BY_LOCALE);
			statement.setString(1, locale);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Category category = new Category();
				category.setId(resultSet.getInt(1));
				category.setCategoryName(resultSet.getString(3));
				categoryList.add(category);
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while categories loading", e);
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return categoryList;
	}
}
