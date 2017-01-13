package com.finalproject.onlineapteka.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.bean.RecipeDetail;
import com.finalproject.onlineapteka.dao.RecipeDao;
import com.finalproject.onlineapteka.dao.RecipeDetailDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.RecipeDetailService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class RecipeDetailServiceImpl implements RecipeDetailService{
	@Override
	public void addRecipeDetail(RecipeDetail recipeDetail) throws ServiceException {
		RecipeDetailDao recipeDetailDao = DAOFactoryImpl.getInstance().getRecipeDetailDao();
		
		try {
			recipeDetailDao.saveRecipeDetail(recipeDetail);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	@Override
	public List<Drug> getDrugsFromRecipe(Integer recipeId) throws ServiceException {
		RecipeDetailDao recipeDetailDao = DAOFactoryImpl.getInstance().getRecipeDetailDao();
		List<Drug> drugList = new ArrayList<>();

		try {
			drugList = recipeDetailDao.loadDrugsFromRecipe(recipeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	
}
