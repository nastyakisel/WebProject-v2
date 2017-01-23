package com.finalproject.onlineapteka.dao;

import java.sql.Date;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.RecipeDetail;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface RecipeDetailDao {
	void saveRecipeDetail(RecipeDetail recipeDetail) throws DAOException;
	List<Drug> loadDrugsFromRecipe(Integer recipeId) throws DAOException;
	List<Integer> loadDrugsByUserId(Integer userId, Date endDate, Integer drugId, Float quantity) throws DAOException;
}
