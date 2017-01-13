package com.finalproject.onlineapteka.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.OrderDetail;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.OrderDetailDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.dao.jdbc.impl.db.DbPool;

public class OrderDetailDaoImpl implements OrderDetailDao {
	private static final String INSERT_ORDERDETAIL = "INSERT INTO orderdetail(quantity, totalprice, FK_DRUG_TO_ORDER, FK_CUSTOMORDER_ID) VALUES (?,?,?,?)";
	
	private static final String SELECT_DRUGS_FROM_ORDER = "SELECT* FROM orderdetail WHERE FK_CUSTOMORDER_ID=?";
	
	/*SELECT *
	  FROM Drug
	  WHERE ID IN
	  (SELECT FK_DRUG_TO_ORDER FROM OrderDetail where FK_CUSTOMORDER_ID=1)*/
	  
	@Override
	public void saveOrderDetail(OrderDetail orderDetail) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(INSERT_ORDERDETAIL);
			statement.setFloat(1, orderDetail.getQuantity());
			statement.setBigDecimal(2, orderDetail.getTotalPriceOfGood());
			statement.setInt(3, orderDetail.getDrugId());
			statement.setInt(4, orderDetail.getCustomOrderId());
			
			statement.executeUpdate();

		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		} finally {
			DbPool.getPool().closeDbResources(connection, statement, resultSet);
		}
	}
	@Override
	public List<Drug> loadDrugsFromOrderDetail(Integer orderId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Drug> drugList = new ArrayList<Drug>();
		DrugDao goodsDao = DAOFactoryImpl.getInstance().getGrugDao();
		try {
			connection = DbPool.getPool().getConnection();
			statement = connection.prepareStatement(SELECT_DRUGS_FROM_ORDER);
			statement.setInt(1, orderId);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Drug drug = null;
				drug = goodsDao.loadDrugById(resultSet.getInt(4));
				drug.setPrice(resultSet.getBigDecimal(3));
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
}
