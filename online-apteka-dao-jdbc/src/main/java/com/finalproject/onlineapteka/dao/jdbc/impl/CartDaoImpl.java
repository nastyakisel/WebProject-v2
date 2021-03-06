package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.CartDao;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class CartDaoImpl implements CartDao {

	private static final Logger LOGGER = LogManager.getLogger(CartDaoImpl.class
			.getName());
	
	private static final String INSERT_GOOD_TO_CART = "INSERT INTO cart(QUANTITY, FK_DRUG_TO_CART, "
			+ "FK_USER_TO_CART) VALUES (?,?,?)";

	private static final String SELECT_DRUGS_FROM_CART = "SELECT* FROM cart WHERE FK_USER_TO_CART=?";

	private static final String DELETE_DRUGS_FROM_CART = "DELETE FROM cart WHERE FK_DRUG_TO_CART=? "
			+ "and FK_USER_TO_CART=?";

	private static final String DELETE_CART = "DELETE FROM cart WHERE FK_USER_TO_CART=?";

	private static final String UPDATE_DRUG_IN_CART = "UPDATE cart SET QUANTITY=? WHERE FK_USER_TO_CART=? and FK_DRUG_TO_CART=?";

	@Override
	public void saveDrugToCart(Integer drugId, Integer userId)
			throws DAOException {
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(INSERT_GOOD_TO_CART)) {
			statement.setInt(1, 1);
			statement.setInt(2, drugId);
			statement.setInt(3, userId);

			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while saving the drugs", e);
			throw new DAOException(e);
		}
	}

	@Override
	public List<Drug> loadDrugsFromCart(Integer userId) throws DAOException {
		DrugDao goodsDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_FROM_CART)) {
			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = goodsDao.loadDrugById(resultSet.getInt(3));
					drug.setQuantity(resultSet.getFloat(2)); // получаем кол-во
																// из cart
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
	public List<Drug> loadDrugsFromCart(Integer userId, String locale)
			throws DAOException {
		DrugDao goodsDao = DAOFactoryImpl.getInstance().getGrugDao();

		List<Drug> drugList = new ArrayList<Drug>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_DRUGS_FROM_CART)) {
			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					Drug drug = goodsDao.loadDrugById(resultSet.getInt(3),
							locale);
					drug.setQuantity(resultSet.getFloat(2)); // получаем кол-во cart
																
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
	public void deleteDrugFromCart(Integer drugId, Integer userId)
			throws DAOException {
		
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(DELETE_DRUGS_FROM_CART)) {
		
			statement.setInt(1, drugId);
			statement.setInt(2, userId);
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while deleting the drugs from cart", e);
			throw new DAOException(e);
		} 
	}

	@Override
	public void deleteCart(Integer userId) throws DAOException {
		
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(DELETE_CART)) {
			
			statement.setInt(1, userId);
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while deleting the cart", e);
			throw new DAOException(e);
		} 
	}

	@Override
	public void updateDrugInCart(Cart cart) throws DAOException {
		
		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(UPDATE_DRUG_IN_CART)) {
			statement.setFloat(1, cart.getQuantity());
			statement.setInt(2, cart.getUserId());
			statement.setInt(3, cart.getDrugId());
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while updating the cart", e);
			throw new DAOException(e);
		} 
	}
}
