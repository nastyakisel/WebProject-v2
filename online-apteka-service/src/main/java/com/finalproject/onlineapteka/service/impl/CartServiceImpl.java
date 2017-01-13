package com.finalproject.onlineapteka.service.impl;

import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.CartDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.CartService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class CartServiceImpl implements CartService{
	@Override
	public void addDrugToCart(Integer drugId, Integer userId) throws ServiceException {
		CartDao cartDao = DAOFactoryImpl.getInstance().getCartDao();
		try {
			cartDao.saveDrugToCart(drugId, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public List<Drug> getDrugsFromCart(Integer userId) throws ServiceException {
		CartDao cartDao = DAOFactoryImpl.getInstance().getCartDao();
		List<Drug> drugList = null;
		try {
			drugList = cartDao.loadDrugsFromCart(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public void removeDrugFromCart(Integer drugId, Integer userId) throws ServiceException {
		CartDao cartDao = DAOFactoryImpl.getInstance().getCartDao();
		try {
			cartDao.deleteDrugFromCart(drugId, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void removeCart(Integer userId) throws ServiceException {
		CartDao cartDao = DAOFactoryImpl.getInstance().getCartDao();
		try {
			cartDao.deleteCart(userId);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void changeCart(Cart cart) throws ServiceException {
		CartDao cartDao = DAOFactoryImpl.getInstance().getCartDao();
		try {
			cartDao.updateDrugInCart(cart);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
