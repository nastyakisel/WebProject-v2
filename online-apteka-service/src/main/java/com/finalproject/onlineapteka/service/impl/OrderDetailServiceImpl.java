package com.finalproject.onlineapteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.OrderDetail;
import com.finalproject.onlineapteka.dao.OrderDetailDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.OrderDetailService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class OrderDetailServiceImpl implements OrderDetailService {
	@Override
	public void addOrderDetail(OrderDetail orderDetail) throws ServiceException {
		OrderDetailDao orderDetailDao = DAOFactoryImpl.getInstance().getOrderDetailDao();

		if (orderDetail.getDrugId() == 0) {
			throw new ServiceException("Empty drugId");
		}

		if (orderDetail.getQuantity() == 0) {
			throw new ServiceException("Empty quantity");
		}
		
		if (orderDetail.getTotalPriceOfGood().equals(null)) {
			throw new ServiceException("Empty price");
		}
		
		try {
			orderDetailDao.saveOrderDetail(orderDetail);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	@Override
	public List<Drug> getDrugsFromOrderDetail(Integer orderId, String locale) throws ServiceException {
		OrderDetailDao orderDetailDao = DAOFactoryImpl.getInstance().getOrderDetailDao();

		if (orderId == 0) {
			throw new ServiceException("Empty orderId");
		}
		
		List<Drug> drugList = new ArrayList<Drug>();
		try {
			drugList = orderDetailDao.loadDrugsFromOrderDetail(orderId, locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	
}
