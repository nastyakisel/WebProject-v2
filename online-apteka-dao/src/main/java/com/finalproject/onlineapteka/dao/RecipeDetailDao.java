package com.finalproject.onlineapteka.dao;

import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.RecipeDetail;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface RecipeDetailDao {
	void saveRecipeDetail(RecipeDetail recipeDetail) throws DAOException;
	List<Drug> loadDrugsFromRecipe(Integer recipeId) throws DAOException;
}
