package com.finalproject.onlineapteka.service;

import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface GoodsService {
	List<Drug> getAllGoods() throws ServiceException;
	List<Drug> getGoodsByCategory(Integer categoryName) throws ServiceException;
	void addGood(Drug drug) throws ServiceException;
	Drug getGoodById(Integer id) throws ServiceException;
	void editGood(Drug drug) throws ServiceException;
	void addDrugToCart(Integer drugId, Integer userId) throws ServiceException;
	List<Drug> getDrugsFromCart(Integer userId) throws ServiceException;
	void removeDrugFromCart(Integer drugId, Integer userId) throws ServiceException;
	void changeCart(Cart cart) throws ServiceException;
}
