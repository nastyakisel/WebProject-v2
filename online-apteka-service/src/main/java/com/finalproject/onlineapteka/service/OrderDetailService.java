package com.finalproject.onlineapteka.service;

import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.OrderDetail;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface OrderDetailService {
	void addOrderDetail(OrderDetail orderDetail) throws ServiceException;
	List<Drug> getDrugsFromOrderDetail(Integer orderId) throws ServiceException;
}
