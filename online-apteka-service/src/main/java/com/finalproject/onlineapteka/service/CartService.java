package com.finalproject.onlineapteka.service;

import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface CartService {
	void addDrugToCart(Integer drugId, Integer userId) throws ServiceException;
	List<Drug> getDrugsFromCart(Integer userId) throws ServiceException;
	void removeDrugFromCart(Integer drugId, Integer userId) throws ServiceException;
	void changeCart(Cart cart) throws ServiceException;
	void removeCart(Integer userId) throws ServiceException;
	List<Drug> getDrugsFromCart(Integer userId, String locale) throws ServiceException;
}
