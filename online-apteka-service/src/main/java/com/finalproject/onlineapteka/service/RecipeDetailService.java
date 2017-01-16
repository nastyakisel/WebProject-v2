package com.finalproject.onlineapteka.service;


import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.RecipeDetail;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface RecipeDetailService {
	void addRecipeDetail(RecipeDetail recipeDetail) throws ServiceException;
	List<Drug> getDrugsFromRecipe(Integer recipeId) throws ServiceException;
}
