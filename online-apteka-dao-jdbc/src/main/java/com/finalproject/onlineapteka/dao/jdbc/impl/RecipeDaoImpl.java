package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.finalproject.onlineapteka.bean.CustomOrder;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.dao.RecipeDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class RecipeDaoImpl implements RecipeDao{
	
	private static final String SELECT_ALL_RECIPES = "SELECT* FROM recipe";
	
	private static final String SELECT_GOOD_BY_ID = "SELECT* FROM recipe WHERE id=?";
	
	private static final String INSERT_RECIPE = "INSERT INTO recipe(DOCTOR_NAME, BEGIN_DATE, END_DATE, FK_USER_TO_RECIPE) VALUES (?,?,?,?)";
	
	private static final String UPDATE_RECIPE = "UPDATE recipe SET END_DATE=? WHERE id=?";
	
	@Override
	public List<Recipe> loadAllRecipes() throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Recipe> recipeList = new ArrayList<>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_ALL_RECIPES);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Recipe recipe = new Recipe();
				recipe.setId(resultSet.getInt(1));
				recipe.setDoctorName(resultSet.getString(2));
				recipe.setBeginDate(resultSet.getDate(3));
				recipe.setEndDate(resultSet.getDate(4));
				recipe.setUserId(resultSet.getInt(5));
				
				recipeList.add(recipe);
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return recipeList;
	}
	@Override
	public Recipe loadRecipeById(Integer recipeId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Recipe recipe = new Recipe();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_GOOD_BY_ID);
			statement.setInt(1, recipeId);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				recipe.setId(resultSet.getInt(1));
				recipe.setDoctorName(resultSet.getString(2));
				recipe.setBeginDate(resultSet.getDate(3));
				recipe.setEndDate(resultSet.getDate(4));
				recipe.setUserId(resultSet.getInt(5));
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return recipe;
	}
	@Override
	public Integer saveRecipe(Recipe recipe) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(INSERT_RECIPE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, recipe.getDoctorName());
			
			statement.setDate(2, recipe.getBeginDate(), java.util.Calendar.getInstance());
			statement.setDate(3, recipe.getEndDate(), java.util.Calendar.getInstance());
			statement.setInt(4, recipe.getUserId());
			
			statement.executeUpdate();
			
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			
			return resultSet.getInt(1);

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}
	@Override
	public void alterRecipe(Date endDate, Integer drugId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(UPDATE_RECIPE);
			
			statement.setDate(1, endDate, java.util.Calendar.getInstance());
			statement.setInt(2, drugId);
			
			statement.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}
}
