package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class BaseDao {
	public ResultSet createResultSet(String sql) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
		} catch (SQLException | InterruptedException e) {
			throw new DAOException(e);
		}
		return resultSet;
	}
}
