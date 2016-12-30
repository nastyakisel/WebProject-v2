package com.finalproject.onlineapteka.dao.jdbc.impl.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DbPool {
	private static final String URL = "jdbc:mysql://localhost:3306/online_apteka?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	private BlockingQueue<Connection> connectionList;

	private static DbPool pool = null;

	public static DbPool getPool() {
		if (pool == null) {
			try {
				pool = new DbPool();
			} catch (ClassNotFoundException | SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return pool;
	}

	private DbPool() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connectionList = new ArrayBlockingQueue<Connection>(8);
	}

	public Connection getConnection() throws SQLException, InterruptedException {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		connectionList.add(connection);
		return connectionList.take();
	}

	public void closeDbResources(Connection connection) {
		closeDbResources(connection, null);
	}

	public void closeDbResources(Connection connection, Statement statement) {
		closeDbResources(connection, statement, null);
	}

	public void closeDbResources(Connection connection, Statement statement,
			ResultSet resultSet) {
		closeResultSet(resultSet);
		closeStatement(statement);
		closeConnection(connection);
	}
	public void closeDbResources(Connection connection, PreparedStatement statement,
			ResultSet resultSet) {
		closeResultSet(resultSet);
		closePreparedStatement(statement);
		closeConnection(connection);
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connectionList.put(connection);
				connectionList.remove(connection);
			} catch (SQLException | InterruptedException e) {
			}
		}
	}

	public void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
	}
	public void closePreparedStatement(PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
	}

	public void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
		}
	}
}
