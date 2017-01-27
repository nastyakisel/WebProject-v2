package com.finalproject.onlineapteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.CustomOrder;
import com.finalproject.onlineapteka.dao.OrderDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.OrderService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class OrderServiceImpl implements OrderService{
	@Override
	public Integer addOrder(CustomOrder customOrder) throws ServiceException {
		OrderDao orderDao = DAOFactoryImpl.getInstance().getOrderDao();

		if (customOrder.getOrderDate().toString().isEmpty()) {
			throw new ServiceException("Empty date");
		}

		if (customOrder.getTotalPrice().equals(null)) {
			throw new ServiceException("Empty price");
		}
		
		if (customOrder.getUserId() == 0) {
			throw new ServiceException("Empty userId");
		}
		
		Integer orderId = null;
		try {
			orderId = orderDao.saveOrder(customOrder);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return orderId;
	}
	
	@Override
	public List<CustomOrder> getOrdersByUserId(Integer userId) throws ServiceException {
		OrderDao orderDao = DAOFactoryImpl.getInstance().getOrderDao();

		if (userId == 0) {
			throw new ServiceException("Empty date");
		}
		List<CustomOrder> customOrderList = new ArrayList<>();
		
		try {
			customOrderList = orderDao.loadOrdersByUserId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return customOrderList;
	}
}
