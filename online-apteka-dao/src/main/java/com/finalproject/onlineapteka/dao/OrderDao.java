package com.finalproject.onlineapteka.dao;

import java.util.List;

import com.finalproject.onlineapteka.bean.CustomOrder;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface OrderDao {
	Integer saveOrder(CustomOrder customOrder) throws DAOException;
	List<CustomOrder> loadOrdersByUserId(Integer userId) throws DAOException;
}
