package com.finalproject.onlineapteka.service.impl;

import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.GoodsDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.GoodsService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class GoodsServiceImpl implements GoodsService {
	@Override
	public List<Drug> getAllGoods() throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();
		List<Drug> drugList = null;

		try {
			drugList = goodsDao.loadAllGoods();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}

	@Override
	public List<Drug> getGoodsByCategory(Integer categoryName)
			throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();
		List<Drug> drugList = null;

		try {
			drugList = goodsDao.loadGoodsByCategory(categoryName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}

	@Override
	public Drug getGoodById(Integer id) throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();
		Drug drug = null;

		try {
			drug = goodsDao.loadGoodById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drug;
	}

	@Override
	public void addGood(Drug drug) throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();

		try {
			goodsDao.saveGood(drug);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void editGood(Drug drug) throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();

		try {
			goodsDao.alterGood(drug);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void addDrugToCart(Integer drugId, Integer userId) throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();
		try {
			goodsDao.saveDrugToCart(drugId, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public List<Drug> getDrugsFromCart(Integer userId) throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();
		List<Drug> drugList = null;
		try {
			drugList = goodsDao.loadDrugsFromCart(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public void removeDrugFromCart(Integer drugId, Integer userId) throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();
		try {
			goodsDao.deleteDrugFromCart(drugId, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void changeCart(Cart cart) throws ServiceException {
		GoodsDao goodsDao = DAOFactoryImpl.getInstance().getGoodsDao();
		try {
			goodsDao.updateDrugInCart(cart);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
