package com.finalproject.onlineapteka.dao;

import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.bean.OrderDetail;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface OrderDetailDao {
	void saveOrderDetail(OrderDetail orderDetail) throws DAOException;
	List<Drug> loadDrugsFromOrderDetail(Integer orderId, String locale) throws DAOException;
}
