package com.finalproject.onlineapteka.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.dao.RecipeDao;
import com.finalproject.onlineapteka.dao.RecipeDetailDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.RecipeService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class RecipeServiceImpl implements RecipeService{
	@Override
	public List<Recipe> getAllRecipes() throws ServiceException {
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		List<Recipe> recipeList = new ArrayList<Recipe>();

		try {
			recipeList = recipeDao.loadAllRecipes();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return recipeList;
	}
	
	@Override
	public Recipe getRecipeById(Integer recipeId) throws ServiceException {
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
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		List<Recipe> recipeList = new ArrayList<Recipe>();

		try {
			recipeList = recipeDao.loadRecipesByUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return recipeList;
	}
	@Override
	public Integer addRecipe(Recipe recipe) throws ServiceException {
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
		RecipeDao recipeDao = DAOFactoryImpl.getInstance().getRecipeDao();
		
		try {
			recipeDao.alterRecipe(endDate, recipeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
}
