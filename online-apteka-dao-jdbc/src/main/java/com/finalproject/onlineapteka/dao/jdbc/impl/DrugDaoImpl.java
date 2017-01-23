package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class DrugDaoImpl implements DrugDao {
	private static final String SELECT_ALL_GOODS = "SELECT* FROM drug";

	private static final String SELECT_ALL_GOODS_BY_LOCALE = "SELECT drug_locale.*, drug.* FROM drug LEFT JOIN drug_locale ON drug.id =drug_locale.id_drug WHERE drug_locale.locale =? and drug.quantity >= '1'";

	private static final String SELECT_DRUGS_BY_CAT = "SELECT* FROM drug WHERE FK_CATEGORY_ID=?";

	private static final String SELECT_DRUGS_BY_CAT_LOCALE = "SELECT* FROM drug_locale, drug WHERE drug.id =drug_locale.ID_DRUG and drug.FK_CATEGORY_ID=? and drug_locale.locale =?";

	private static final String SELECT_GOOD_BY_ID = "SELECT* FROM drug WHERE id=?";

	private static final String SELECT_GOOD_BY_ID_LOCALE = "SELECT drug_locale.*, drug.* FROM drug LEFT JOIN drug_locale ON drug.id =drug_locale.id_drug WHERE drug_locale.locale =? and drug_locale.ID_DRUG=?";

	private static final String SELECT_DRUGS_BY_RECIPE = "SELECT* FROM drug WHERE NEED_RECIPE=?";

	private static final String SELECT_DRUGS_BY_NAME = "SELECT* FROM drug WHERE drugname LIKE ?";

	private static final String SELECT_DRUGS_BY_NAME_LOCALE = "SELECT drug_locale.*, drug.* FROM drug LEFT JOIN drug_locale ON drug.id =drug_locale.id_drug WHERE drug_locale.locale =? and  drug_locale.drugname=?";

	private static final String INSERT_GOOD = "INSERT INTO drug(drugname, description, dosage, "
			+ "instruction, price, quantity, need_recipe, image_path, FK_CATEGORY_ID) VALUES (?,?,?,?,?,?,?,?,?)";
	
	private static final String INSERT_GOOD_LOCALE = "INSERT INTO drug_locale(drugname, description, dosage, "
			+ "instruction, price, quantity, need_recipe, image_path, FK_CATEGORY_ID) VALUES (?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE_GOOD = "UPDATE drug SET drugname=?, description=?, dosage=?, instruction=?,"
			+ "price=?, quantity=?, need_recipe=?, image_path=?, FK_CATEGORY_ID=? WHERE id =?";
	
	private static final String DELETE_DRUG = "DELETE FROM drug WHERE id=?";

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
					drug.setPrice(resultSet.getBigDecimal(12));
					drug.setQuantity(resultSet.getFloat(13));
					drug.setNeedRecipe(resultSet.getInt(14));
					drug.setImagePath(resultSet.getString(15));
					drug.setCategoryId(resultSet.getInt(16));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
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
					drug.setPrice(resultSet.getBigDecimal(12));
					drug.setQuantity(resultSet.getFloat(13));
					drug.setNeedRecipe(resultSet.getInt(14));
					drug.setImagePath(resultSet.getString(15));
					drug.setCategoryId(resultSet.getInt(16));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return drugList;
	}

	@Override
	public Drug loadDrugById(Integer id) throws DAOException {

		Drug drug = new Drug();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_GOOD_BY_ID)) {
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
			throw new DAOException(e);
		}
		return drug;
	}

	@Override
	public Drug loadDrugById(Integer id, String locale) throws DAOException {

		Drug drug = new Drug();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_GOOD_BY_ID_LOCALE)) {
			statement.setInt(2, id);
			statement.setString(1, locale);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Integer id1 = resultSet.getInt(1);
					drug.setId(resultSet.getInt(1));
					drug.setDrugName(resultSet.getString(3));
					drug.setDescription(resultSet.getString(4));
					drug.setDosage(resultSet.getString(5));
					drug.setInstruction(resultSet.getString(6));
					drug.setPrice(resultSet.getBigDecimal(12));
					drug.setQuantity(resultSet.getFloat(13));
					drug.setNeedRecipe(resultSet.getInt(14));
					drug.setImagePath(resultSet.getString(15));
					drug.setCategoryId(resultSet.getInt(16));
				}
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return drug;
	}

	@Override
	public void saveDrug(Drug drug) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(INSERT_GOOD, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, drug.getDrugName());
			statement.setString(2, drug.getDescription());
			statement.setString(3, drug.getDosage());
			statement.setString(4, drug.getInstruction());
			statement.setBigDecimal(5, drug.getPrice());
			statement.setFloat(6, drug.getQuantity());
			statement.setInt(7, drug.getNeedRecipe());
			statement.setString(8, drug.getImagePath());
			statement.setInt(9, drug.getCategoryId());
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
	}
	public void saveDrug(Drug drug, String locale, Integer id) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(INSERT_GOOD_LOCALE)) {
			statement.setString(1, drug.getDrugName());
			statement.setString(2, drug.getDescription());
			statement.setString(3, drug.getDosage());
			statement.setString(4, drug.getInstruction());
			statement.setBigDecimal(5, drug.getPrice());
			statement.setFloat(6, drug.getQuantity());
			statement.setInt(7, drug.getNeedRecipe());
			statement.setString(8, drug.getImagePath());
			statement.setInt(9, drug.getCategoryId());
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void alterDrug(Drug drug) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(UPDATE_GOOD)) {
			statement.setString(1, drug.getDrugName());
			statement.setString(2, drug.getDescription());
			statement.setString(3, drug.getDosage());
			statement.setString(4, drug.getInstruction());
			statement.setBigDecimal(5, drug.getPrice());
			statement.setFloat(6, drug.getQuantity());
			statement.setInt(7, drug.getNeedRecipe());
			statement.setString(8, drug.getImagePath());
			statement.setInt(9, drug.getCategoryId());
			statement.setInt(10, drug.getId());

			statement.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void deleteDrug(Integer drugId) throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(DELETE_DRUG)) {
			statement.setInt(1, drugId);
			statement.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Drug> loadDrugsByRecipe(Integer needRecipe) throws DAOException {

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_BY_RECIPE)) {
			statement.setInt(1, needRecipe);

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
					drug.setPrice(resultSet.getBigDecimal(12));
					drug.setQuantity(resultSet.getFloat(13));
					drug.setNeedRecipe(resultSet.getInt(14));
					drug.setImagePath(resultSet.getString(15));
					drug.setCategoryId(resultSet.getInt(16));
					drugList.add(drug);
				}
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return drugList;
	}
}
