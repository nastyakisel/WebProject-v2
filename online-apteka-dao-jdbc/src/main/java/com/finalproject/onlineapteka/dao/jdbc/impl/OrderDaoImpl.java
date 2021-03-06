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

import com.finalproject.onlineapteka.bean.CustomOrder;
import com.finalproject.onlineapteka.dao.OrderDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class OrderDaoImpl implements OrderDao {

	private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class
			.getName());
	
	private static final String INSERT_ORDER = "INSERT INTO customorder(ORDER_DATE, totalprice, ORDER_STATUS, FK_USER_TO_ORDER) VALUES (?,?,?,?)";

	private static final String SELECT_ORDER_BY_USERID = "SELECT* FROM customorder WHERE FK_USER_TO_ORDER=?";

	@Override
	public Integer saveOrder(CustomOrder customOrder) throws DAOException {

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

			// statement.setDate(1, customOrder.getOrderDate());
			statement.setDate(1, customOrder.getOrderDate(),
					java.util.Calendar.getInstance());
			statement.setBigDecimal(2, customOrder.getTotalPrice());
			statement.setInt(3, customOrder.getOrderStatus());
			statement.setInt(4, customOrder.getUserId());

			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				resultSet.next();

				return resultSet.getInt(1);
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while saving the order", e);
			throw new DAOException(e);
		}
	}

	@Override
	public List<CustomOrder> loadOrdersByUserId(Integer userId)
			throws DAOException {

		List<CustomOrder> customOrderList = new ArrayList<CustomOrder>();

		try (Connection connection = DbPool.getPool().getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_ORDER_BY_USERID)) {
			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					CustomOrder customOrder = new CustomOrder();
					customOrder.setId(resultSet.getInt(1));
					customOrder.setOrderDate(resultSet.getDate(2));
					customOrder.setTotalPrice(resultSet.getBigDecimal(3));
					customOrder.setOrderStatus(resultSet.getInt(4));
					customOrder.setUserId(userId);
					customOrderList.add(customOrder);
				}
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the order", e);
			throw new DAOException(e);
		}
		return customOrderList;
	}
}
