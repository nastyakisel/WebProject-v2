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
import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class UserDaoImpl implements UserDao {
	private static final String SELECT_USER = "SELECT* FROM user WHERE user_name=?";
	
	private static final String INSERT_USER = "INSERT INTO user(user_name, password, FK_ROLE_ID, FIRST_NAME, SECOND_NAME) VALUES (?, ?, ?, ?, ?)";
	
	private static final String SELECT_USER_BY_ID = "SELECT* FROM user WHERE id=?";

	private static final String SELECT_USERS_BY_ROLE = "SELECT* FROM user WHERE FK_ROLE_ID=?";
	
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class
			.getName());

	@Override
	public User loadUser(String userName) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DbPool.getPool().getConnection();
			
			statement = connection.prepareStatement(SELECT_USER);
			statement.setString(1, userName);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				User user = new User();
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("user_name");
				String password = resultSet.getString("password");
				Integer roleId = resultSet.getInt("FK_ROLE_ID");
				String first_name = resultSet.getString("first_name");
				String second_name = resultSet.getString("second_name");

				user.setId(id);
				user.setUserName(name);
				user.setPassword(password);
				user.setRoleId(roleId);
				user.setFirstName(first_name);
				user.setSecondName(second_name);
				return user;
			}
		} catch (SQLException e) {
			LOGGER.error("Error occurs while loading the user", e);
			throw new DAOException(e);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException while loading the user", e);
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return null;
	}

	@Override
	public void saveUser(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(INSERT_USER);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getRoleId());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getSecondName());
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while saving the user", e);
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}
	@Override
	public User loadUserById(Integer userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_USER_BY_ID);
			statement.setInt(1, userId);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("user_name");
				String password = resultSet.getString("password");
				Integer role = resultSet.getInt("FK_ROLE_ID");
				String first_name = resultSet.getString("first_name");
				String second_name = resultSet.getString("second_name");

				User user = new User();
				user.setId(userId);
				user.setUserName(name);
				user.setPassword(password);
				user.setRoleId(role);
				user.setFirstName(first_name);
				user.setSecondName(second_name);

				return user;
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the user", e);
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return null;
	}
	@Override
	public List<User> loadUsersByRole(Integer role) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<User> userList = new ArrayList<User>();

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_USERS_BY_ROLE);
			statement.setInt(1, role);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setUserName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRoleId(role);
				user.setFirstName(resultSet.getString("first_name"));
				user.setSecondName(resultSet.getString("second_name"));
	
				userList.add(user);
			}
		} catch (InterruptedException | SQLException e) {
			LOGGER.error("Error occurs while loading the user", e);
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
		return userList;
	}
}
