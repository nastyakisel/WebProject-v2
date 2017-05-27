package com.finalproject.onlineapteka.dao.jdbc.impl.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DbPool {

	private static final Logger LOGGER = LogManager
				.getLogger(DbPool.class.getName());
		private static final int POOLSIZE = 8;
		private BlockingQueue<Connection> takenConnections;
		private Properties properties;
		private String url;
		private String user;
		private String password;
		private String driver;

		private static DbPool pool = null;

		public synchronized static DbPool getPool() {
			if (pool == null) {
				try {
					pool = new DbPool();
				} catch (Exception e) {
					throw new RuntimeException("The pool is not created", e);
				}
			}
			return pool;
		}

		private Properties getProperties() {
			Properties property = new Properties();
			try {
				InputStream stream = getClass().getClassLoader()
						.getResourceAsStream("dbConfig.properties");
				property.load(stream);
			} catch (IOException e) {
				throw new RuntimeException("The properties file is not founded ", e);
			}
			return property;
		}

		private DbPool() {
			takenConnections = new ArrayBlockingQueue<Connection>(POOLSIZE);
			properties = getProperties();
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");
			driver = properties.getProperty("jdbc.driver");
		}

		public Connection getConnection() throws SQLException,
				InterruptedException, ClassNotFoundException {
			Class.forName(driver);
			Connection connection = DriverManager
					.getConnection(url, user, password);
			removeConnection();
			try {
				takenConnections.add(connection);
			} catch (IllegalStateException e) {
				LOGGER.info("Waiting for connection");
			}
			return connection;
		}

		private void removeConnection() throws SQLException  {
			if (takenConnections.remainingCapacity() == 0) {
				for (Connection connection : takenConnections) {
					if (connection.isClosed()) {
						takenConnections.remove(connection);
						break;
					}
				}
			}
	}
