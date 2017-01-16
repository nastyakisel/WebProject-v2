package com.finalproject.onlineapteka.service;

import java.util.List;

import com.finalproject.onlineapteka.bean.CustomOrder;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface OrderService {
	Integer addOrder(CustomOrder customOrder) throws ServiceException;
	List<CustomOrder> getOrdersByUserId(Integer userId) throws ServiceException;
}
