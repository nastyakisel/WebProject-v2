package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class GoodsDaoImpl implements GoodsDao {

	private static final String SELECT_ALL_GOODS = "SELECT* FROM drug";

	private static final String SELECT_GOODS_BY_CAT = "SELECT* FROM drug WHERE FK_CATEGORY_ID=?";

	private static final String SELECT_GOOD_BY_ID = "SELECT* FROM drug WHERE id=?";

	private static final String INSERT_GOOD = "INSERT INTO drug(drugname, description, dosage, "
			+ "instruction, price, quantity, need_recipe, image_path, FK_CATEGORY_ID) VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_GOOD = "UPDATE drug SET drugname=?, description=?, dosage=?, instruction=?,"
			+ "price=?, quantity=?, need_recipe=?, image_path=?, FK_CATEGORY_ID=? WHERE id =?";
	
	private static final String INSERT_GOOD_TO_CART = "INSERT INTO cart(QUANTITY, FK_DRUG_TO_CART, "
			+ "FK_USER_TO_CART) VALUES (?,?,?)";
	
	private static final String SELECT_DRUGS_FROM_CART = "SELECT* FROM cart WHERE FK_USER_TO_CART=?";
	
	private static final String DELETE_DRUGS_FROM_CART = "DELETE FROM cart WHERE FK_DRUG_TO_CART=? "
			+ "and FK_USER_TO_CART=?";
	
	private static final String UPDATE_DRUG_IN_CART = "UPDATE cart SET QUANTITY=? WHERE FK_USER_TO_CART=? and FK_DRUG_TO_CART=?";
	
	@Override
	public List<Drug> loadAllGoods() throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Drug> drugList = new ArrayList<Drug>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_ALL_GOODS);
			resultSet = statement.executeQuery();

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
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return drugList;
	}

	@Override
	public List<Drug> loadGoodsByCategory(Integer categoryId)
			throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Drug> drugList = new ArrayList<Drug>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_GOODS_BY_CAT);
			statement.setInt(1, categoryId);

			resultSet = statement.executeQuery();

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
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return drugList;
	}

	@Override
	public Drug loadGoodById(Integer id) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Drug drug = new Drug();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_GOOD_BY_ID);
			statement.setInt(1, id);

			resultSet = statement.executeQuery();

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
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return drug;
	}

	@Override
	public void saveGood(Drug drug) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(INSERT_GOOD);
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
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}

	@Override
	public void alterGood(Drug drug) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(UPDATE_GOOD);
			
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
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}
	@Override
	public void saveDrugToCart(Integer drugId, Integer userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(INSERT_GOOD_TO_CART);
			statement.setInt(1, 1);
			statement.setInt(2, drugId);
			statement.setInt(3, userId);
			
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}
	@Override
	public List<Drug> loadDrugsFromCart(Integer userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Drug> drugList = new ArrayList<Drug>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_DRUGS_FROM_CART);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Drug drug = loadGoodById(resultSet.getInt(3));
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
	@Override
	public void deleteDrugFromCart(Integer drugId, Integer userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(DELETE_DRUGS_FROM_CART);
			statement.setInt(1, drugId);
			statement.setInt(2, userId);
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement);
		}
	}
	@Override
	public void updateDrugInCart(Cart cart) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(UPDATE_DRUG_IN_CART);
			statement.setFloat(1, cart.getQuantity());
			statement.setInt(2, cart.getUserId());
			statement.setInt(3, cart.getDrugId());
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement);
		}
	}
}
