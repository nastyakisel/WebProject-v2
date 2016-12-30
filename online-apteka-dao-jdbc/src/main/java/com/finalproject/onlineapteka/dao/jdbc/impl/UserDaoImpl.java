package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class UserDaoImpl implements UserDao {
	private static final String SELECT_USER = "SELECT id, user_name, password, FK_ROLE_ID FROM user WHERE user_name=?";
	
	private static final String INSERT_USER = "INSERT INTO user(user_name, password, FK_ROLE_ID) VALUES (?, ?, ?)";
	
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class
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

				user.setId(id);
				user.setUserName(name);
				user.setPassword(password);
				user.setRoleId(roleId);
				return user;
			}
		} catch (SQLException e) {
			logger.error("SQLExeption", e);
			throw new DAOException(e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
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
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}
}
