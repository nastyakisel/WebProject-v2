package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class DrugDaoImpl implements DrugDao {
	
	private static final Logger LOGGER = LogManager.getLogger(DrugDaoImpl.class
			.getName());
	private static final String SELECT_ALL_GOODS = "SELECT* FROM drug";

	private static final String SELECT_ALL_GOODS_BY_LOCALE = "SELECT drug_locale.*, drug.* FROM drug LEFT JOIN drug_locale ON drug.id =drug_locale.id_drug WHERE drug_locale.locale=? and drug.quantity >= '1'";

	private static final String SELECT_DRUGS_BY_CAT = "SELECT* FROM drug WHERE FK_CATEGORY_ID=?";

	private static final String SELECT_DRUGS_BY_CAT_LOCALE = "SELECT* FROM drug_locale, drug WHERE drug.id =drug_locale.ID_DRUG and drug.FK_CATEGORY_ID=? and drug_locale.locale=?";

	private static final String SELECT_DRUG_BY_ID = "SELECT* FROM drug WHERE id=?";

	private static final String SELECT_DRUG_BY_ID_LOCALE = "SELECT drug_locale.*, drug.* FROM drug LEFT JOIN drug_locale ON drug.id =drug_locale.id_drug WHERE drug_locale.locale=? and drug_locale.ID_DRUG=?";
	
	private static final String SELECT_DRUGS_BY_RECIPE_LOCALE = "SELECT drug_locale.*, drug.* FROM drug LEFT JOIN drug_locale ON drug.id =drug_locale.id_drug WHERE drug_locale.locale=? and drug.NEED_RECIPE=?";

	private static final String SELECT_DRUGS_BY_NAME = "SELECT* FROM drug WHERE drugname LIKE ?";

	private static final String SELECT_DRUGS_BY_NAME_LOCALE = "SELECT drug_locale.*, drug.* FROM drug LEFT JOIN drug_locale ON drug.id =drug_locale.id_drug WHERE drug_locale.drugname LIKE ? and drug_locale.locale=?";

	private static final String INSERT_DRUG = "INSERT INTO drug(price, quantity, need_recipe, image_path, FK_CATEGORY_ID) VALUES (?,?,?,?,?)";
	
	private static final String INSERT_DRUG_LOCALE = "INSERT INTO drug_locale(ID_DRUG, locale, drugname, description, dosage, instruction) VALUES (?,?,?,?,?,?)";
	
	private static final String UPDATE_DRUG = "UPDATE drug SET price=?, quantity=?, need_recipe=?, image_path=?, FK_CATEGORY_ID=? WHERE id =?";
	
	private static final String UPDATE_DRUG_LOCALE = "UPDATE drug_locale SET drugname=?, description=?, dosage=?, instruction=? WHERE ID_DRUG =? and locale=?";
	
	private static final String DELETE_DRUG = "DELETE FROM drug WHERE id=?";
	
	private static final String DELETE_DRUG_LOCALE = "DELETE FROM drug_locale WHERE ID_DRUG=?";

	@Override
	public List<Drug> loadAllDrugs() throws DAOException {

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_ALL_GOODS)) {

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Drug drug = new Drug();
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(2));
					drug.setDescription(resultSet.getString(3));
					drug.setDosage(resultSet.getString(4));
					drug.setInstruction(resultSet.getString(5));
					drug.setPrice(resultSet.getBigDecimal(6));
					drug.setQuantity(resultSet.getFloat(7));
					drug.setNeedRecipe(resultSet.getInt(8));
					drug.setImagePath(resultSet.getString(9));
					drug.setCategoryId(resultSet.getInt(10));
					drugList.add(drug);
				}
			}
		} catch (SQLException | InterruptedException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}
		return drugList;
	}

	@Override
	public List<Drug> loadAllDrugs(String locale) throws DAOException {

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_ALL_GOODS_BY_LOCALE)) {
			statement.setString(1, locale);
			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = new Drug();
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(3));
					drug.setDescription(resultSet.getString(4));
					drug.setDosage(resultSet.getString(5));
					drug.setInstruction(resultSet.getString(6));
					drug.setPrice(resultSet.getBigDecimal(8));
					drug.setQuantity(resultSet.getFloat(9));
					drug.setNeedRecipe(resultSet.getInt(10));
					drug.setImagePath(resultSet.getString(11));
					drug.setCategoryId(resultSet.getInt(12));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}

		return drugList;
	}

	@Override
	public List<Drug> loadDrugsByCategory(Integer categoryId)
			throws DAOException {

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_BY_CAT)) {
			statement.setInt(1, categoryId);
			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = new Drug();
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(2));
					drug.setDescription(resultSet.getString(3));
					drug.setDosage(resultSet.getString(4));
					drug.setInstruction(resultSet.getString(5));
					drug.setPrice(resultSet.getBigDecimal(6));
					drug.setQuantity(resultSet.getFloat(7));
					drug.setNeedRecipe(resultSet.getInt(8));
					drug.setImagePath(resultSet.getString(9));
					drug.setCategoryId(resultSet.getInt(10));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}
		return drugList;
	}

	@Override
	public List<Drug> loadDrugsByCategory(Integer categoryId, String locale)
			throws DAOException {

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_BY_CAT_LOCALE)) {
			statement.setInt(1, categoryId);
			statement.setString(2, locale);
			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = new Drug();
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(3));
					drug.setDescription(resultSet.getString(4));
					drug.setDosage(resultSet.getString(5));
					drug.setInstruction(resultSet.getString(6));
					drug.setPrice(resultSet.getBigDecimal(8));
					drug.setQuantity(resultSet.getFloat(9));
					drug.setNeedRecipe(resultSet.getInt(10));
					drug.setImagePath(resultSet.getString(11));
					drug.setCategoryId(resultSet.getInt(12));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}
		return drugList;
	}

	@Override
	public Drug loadDrugById(Integer id) throws DAOException {

		Drug drug = new Drug();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUG_BY_ID)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(2));
					drug.setDescription(resultSet.getString(3));
					drug.setDosage(resultSet.getString(4));
					drug.setInstruction(resultSet.getString(5));
					drug.setPrice(resultSet.getBigDecimal(6));
					drug.setQuantity(resultSet.getFloat(7));
					drug.setNeedRecipe(resultSet.getInt(8));
					drug.setImagePath(resultSet.getString(9));
					drug.setCategoryId(resultSet.getInt(10));
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}
		return drug;
	}

	@Override
	public Drug loadDrugById(Integer id, String locale) throws DAOException {

		Drug drug = new Drug();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUG_BY_ID_LOCALE)) {
			statement.setInt(2, id);
			statement.setString(1, locale);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(3));
					drug.setDescription(resultSet.getString(4));
					drug.setDosage(resultSet.getString(5));
					drug.setInstruction(resultSet.getString(6));
					drug.setPrice(resultSet.getBigDecimal(8));
					drug.setQuantity(resultSet.getFloat(9));
					drug.setNeedRecipe(resultSet.getInt(10));
					drug.setImagePath(resultSet.getString(11));
					drug.setCategoryId(resultSet.getInt(12));
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}
		return drug;
	}

	@Override
	public Integer saveDrug(Drug drug) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(INSERT_DRUG, Statement.RETURN_GENERATED_KEYS)) {
			
			statement.setBigDecimal(1, drug.getPrice());
			statement.setFloat(2, drug.getQuantity());
			statement.setInt(3, drug.getNeedRecipe());
			statement.setString(4, drug.getImagePath());
			statement.setInt(5, drug.getCategoryId());
			statement.executeUpdate();
			
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				resultSet.next();

				return resultSet.getInt(1);
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while saving the drugs", e);
			throw new DAOException(e);
		}
	}
	@Override
	public void saveDrug(Drug drug, String locale, Integer drugId) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(INSERT_DRUG_LOCALE)) {
			
			statement.setInt(1, drugId);
			statement.setString(2, locale);
			statement.setString(3, drug.getDrugName());
			statement.setString(4, drug.getDescription());
			statement.setString(5, drug.getDosage());
			statement.setString(6, drug.getInstruction());
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while saving the drugs", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void alterDrug(Drug drug) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(UPDATE_DRUG)) {
			
			statement.setBigDecimal(1, drug.getPrice());
			statement.setFloat(2, drug.getQuantity());
			statement.setInt(3, drug.getNeedRecipe());
			statement.setString(4, drug.getImagePath());
			statement.setInt(5, drug.getCategoryId());
			statement.setInt(6, drug.getId());

			statement.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while altering the drugs", e);
			throw new DAOException(e);
		}
	}
	@Override
	public void alterDrug(Drug drug, String locale) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(UPDATE_DRUG_LOCALE)) {
			
			statement.setString(1, drug.getDrugName());
			statement.setString(2, drug.getDescription());
			statement.setString(3, drug.getDosage());
			statement.setString(4, drug.getInstruction());
			statement.setInt(5, drug.getId());
			statement.setString(6, locale);

			statement.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void deleteDrug(Integer drugId) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(DELETE_DRUG);
				PreparedStatement statement2 = connection
						.prepareStatement(DELETE_DRUG_LOCALE))
				{
			statement.setInt(1, drugId);
			statement2.setInt(1, drugId);
			statement.executeUpdate();
			statement2.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while altering the drugs", e);
			throw new DAOException(e);
		}
	}

	@Override
	public List<Drug> loadDrugsByRecipe(Integer needRecipe, String locale) throws DAOException {

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_BY_RECIPE_LOCALE)) {
			statement.setString(1, locale);
			statement.setInt(2, needRecipe);
			
			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = new Drug();
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(3));
					drug.setDescription(resultSet.getString(4));
					drug.setDosage(resultSet.getString(5));
					drug.setInstruction(resultSet.getString(6));
					drug.setPrice(resultSet.getBigDecimal(8));
					drug.setQuantity(resultSet.getFloat(9));
					drug.setNeedRecipe(resultSet.getInt(10));
					drug.setImagePath(resultSet.getString(11));
					drug.setCategoryId(resultSet.getInt(12));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}
		return drugList;
	}

	@Override
	public List<Drug> loadDrugsByName(String drugName) throws DAOException {

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_BY_NAME)) {
			statement.setString(1, "%" + drugName + "%");

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = new Drug();
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(2));
					drug.setDescription(resultSet.getString(3));
					drug.setDosage(resultSet.getString(4));
					drug.setInstruction(resultSet.getString(5));
					drug.setPrice(resultSet.getBigDecimal(6));
					drug.setQuantity(resultSet.getFloat(7));
					drug.setNeedRecipe(resultSet.getInt(8));
					drug.setImagePath(resultSet.getString(9));
					drug.setCategoryId(resultSet.getInt(10));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}
		return drugList;
	}

	@Override
	public List<Drug> loadDrugsByName(String drugName, String locale)
			throws DAOException {

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_BY_NAME_LOCALE)) {
			statement.setString(1, "%" + drugName + "%");
			statement.setString(2, locale);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = new Drug();
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(3));
					drug.setDescription(resultSet.getString(4));
					drug.setDosage(resultSet.getString(5));
					drug.setInstruction(resultSet.getString(6));
					drug.setPrice(resultSet.getBigDecimal(8));
					drug.setQuantity(resultSet.getFloat(9));
					drug.setNeedRecipe(resultSet.getInt(10));
					drug.setImagePath(resultSet.getString(11));
					drug.setCategoryId(resultSet.getInt(12));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the drugs", e);
			throw new DAOException(e);
		}
		return drugList;
	}
}
