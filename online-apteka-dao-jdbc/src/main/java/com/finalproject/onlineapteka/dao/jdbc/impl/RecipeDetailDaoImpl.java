package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.RecipeDetail;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.RecipeDetailDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class RecipeDetailDaoImpl implements RecipeDetailDao {

	private static final Logger LOGGER = LogManager.getLogger(RecipeDetailDaoImpl.class
			.getName());
	
	private static final String INSERT_RECIPEDETAIL = "INSERT INTO recipedetail(dosage, quantity, FK_DRUG_TO_RECIPE, FK_Recipe_ID) VALUES (?,?,?,?)";

	private static final String SELECT_DRUGS_BY_RECIPE_ID = "SELECT* FROM recipedetail WHERE FK_Recipe_ID=?";
	
	private static final String SELECT_DRUGS_BY_USER_ID = "SELECT FK_Recipe_ID FROM recipedetail WHERE FK_Recipe_ID in (SELECT ID FROM recipe where FK_USER_TO_RECIPE = ? and END_DATE >= ? and FK_DRUG_TO_RECIPE=? and quantity >=?)";

	@Override
	public void saveRecipeDetail(RecipeDetail recipeDetail) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(INSERT_RECIPEDETAIL)) {

			statement.setString(1, recipeDetail.getDosage());
			statement.setFloat(2, recipeDetail.getQuantity());
			statement.setInt(3, recipeDetail.getDrugId());
			statement.setInt(4, recipeDetail.getRecipeId());

			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while saving the recipe details", e);
			throw new DAOException(e);
		}
	}

	@Override
	public List<Drug> loadDrugsFromRecipe(Integer recipeId, String locale) throws DAOException {

		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = new ArrayList<>();
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_BY_RECIPE_ID)) {
			statement.setInt(1, recipeId);
			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = null;
					drug = drugDao.loadDrugById(resultSet.getInt(3), locale);
					drug.setDosage(resultSet.getString(1));
					drug.setQuantity(resultSet.getFloat(2));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs from recipe", e);
			throw new DAOException(e);
		}
		return drugList;
	}
	@Override
	public List<Integer> loadDrugsByUserId(Integer userId, Date endDate, Integer drugId, Float quantity) throws DAOException {

		List<Integer> recipeList = new ArrayList<>();
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_BY_USER_ID)) {
			statement.setInt(1, userId);
			statement.setDate(2, endDate);
			statement.setInt(3, drugId);
			statement.setFloat(4, quantity);
			
			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Integer recipeId = 0;
					recipeId = resultSet.getInt(1);
					recipeList.add(recipeId);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs from recipe", e);
			throw new DAOException(e);
		}
		return recipeList;
	}
}
