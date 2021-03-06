package com.finalproject.onlineapteka.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.dao.RecipeDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class RecipeServiceImpl implements RecipeService{
	@Override
	public List<Recipe> getAllRecipes() throws ServiceException {
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		List<Recipe> recipeList = new ArrayList<>();

		try {
			recipeList = recipeDao.loadAllRecipes();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return recipeList;
	}
	
	@Override
	public Recipe getRecipeById(Integer recipeId) throws ServiceException {
		if(recipeId == null) {
			throw new ServiceException("Empty recipeId!");
		}
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		Recipe recipe = null;

		try {
			recipe = recipeDao.loadRecipeById(recipeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return recipe;
	}
	@Override
	public List<Recipe> getRecipesByUser(Integer userId) throws ServiceException {
		if(userId == null) {
			throw new ServiceException("Empty userId!");
		}
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		List<Recipe> recipeList = new ArrayList<>();

		try {
			recipeList = recipeDao.loadRecipesByUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return recipeList;
	}
	@Override
	public Integer addRecipe(Recipe recipe) throws ServiceException {
		if(recipe == null) {
			throw new ServiceException("Empty recipe!");
		}
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		Integer recipeId = null;
		try {
			recipeId = recipeDao.saveRecipe(recipe);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return recipeId;
	}
	
	@Override
	public void updateRecipe(Date endDate, Integer recipeId) throws ServiceException {
		if(endDate == null) {
			throw new ServiceException("Empty endDate!");
		}
		if(recipeId == null) {
			throw new ServiceException("Empty recipeId!");
		}
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		
		try {
			recipeDao.alterRecipe(endDate, recipeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void updateRecipeRequest(Integer hasRequest, Date dateOfRequest, Integer recipeId) throws ServiceException {
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		
		try {
			recipeDao.alterRecipeRequest(hasRequest, dateOfRequest, recipeId);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
