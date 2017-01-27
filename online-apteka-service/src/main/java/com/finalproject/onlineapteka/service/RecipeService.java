package com.finalproject.onlineapteka.service;

import java.sql.Date;
import java.util.List;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface RecipeService {
	List<Recipe> getAllRecipes() throws ServiceException;
	Integer addRecipe(Recipe recipe) throws ServiceException;
	Recipe getRecipeById(Integer recipeId) throws ServiceException;
	void updateRecipe(Date endDate, Integer drugId) throws ServiceException;
	List<Recipe> getRecipesByUser(Integer userId) throws ServiceException;
	void updateRecipeRequest(Integer hasRequest, Date dateOfRequest, Integer recipeId) throws ServiceException;
}
