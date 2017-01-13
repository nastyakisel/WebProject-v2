package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.Recipe;
import com.finalproject.onlineapteka.bean.RecipeDetail;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.RecipeDetailDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class RecipeDetailDaoImpl implements RecipeDetailDao {
	
	private static final String INSERT_RECIPEDETAIL = "INSERT INTO recipedetail(dosage, quantity, FK_DRUG_TO_RECIPE, FK_Recipe_ID) VALUES (?,?,?,?)";
	
	private static final String SELECT_GRUGS_FROM_RECIPE = "SELECT* FROM recipedetail WHERE FK_Recipe_ID=?";
	
	@Override
	public void saveRecipeDetail(RecipeDetail recipeDetail) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(INSERT_RECIPEDETAIL);
			statement.setString(1, recipeDetail.getDosage());
			statement.setFloat(2, recipeDetail.getQuantity());
			statement.setInt(3, recipeDetail.getDrugId());
			statement.setInt(4, recipeDetail.getRecipeId());
			
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}
	@Override
	public List<Drug> loadDrugsFromRecipe(Integer recipeId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = new ArrayList<>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_GRUGS_FROM_RECIPE);
			statement.setInt(1, recipeId);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Drug drug = null;
				drug = drugDao.loadDrugById(resultSet.getInt(3));
				drug.setDosage(resultSet.getString(1));
				drug.setQuantity(resultSet.getFloat(2));
				drugList.add(drug);
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return drugList;
	}
}
