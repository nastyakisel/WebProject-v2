package com.finalproject.onlineapteka.dao;

import java.sql.Date;
import java.util.List;

import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface RecipeDao {
	List<Recipe> loadAllRecipes() throws DAOException;
	Integer saveRecipe(Recipe recipe) throws DAOException;
	Recipe loadRecipeById(Integer recipeId) throws DAOException;
	void alterRecipe(Date endDate, Integer drugId) throws DAOException;
}
