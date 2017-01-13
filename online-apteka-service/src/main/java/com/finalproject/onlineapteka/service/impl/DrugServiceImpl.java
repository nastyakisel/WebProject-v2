package com.finalproject.onlineapteka.service.impl;

import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.DrugDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.DrugService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class DrugServiceImpl implements DrugService {
	@Override
	public List<Drug> getAllDrugs() throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = null;

		try {
			drugList = drugDao.loadAllDrugs();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public List<Drug> getAllDrugs(String locale) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		if (locale.isEmpty()) {
			throw new ServiceException("Empty locale!");
		}
		
		List<Drug> drugList = null;

		try {
			drugList = drugDao.loadAllDrugs(locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	
	@Override
	public List<Drug> getGrugsByCategory(Integer categoryId)
			throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = null;

		try {
			drugList = drugDao.loadDrugsByCategory(categoryId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public List<Drug> getGrugsByCategory(Integer categoryId, String locale)
			throws ServiceException {
		if(categoryId == null) {
			throw new ServiceException("Empty categoryName!");
		}
		if(locale.equals(null)) {
			throw new ServiceException("Empty locale!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = null;

		try {
			drugList = drugDao.loadDrugsByCategory(categoryId, locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	
	@Override
	public List<Drug> getDrugsByName(String drugName)
			throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = null;

		try {
			drugList = drugDao.loadDrugsByName(drugName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public Drug getDrugById(Integer id) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		if(id == null) {
			throw new ServiceException("Empty id!");
		}
		Drug drug = null;

		try {
			drug = drugDao.loadDrugById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drug;
	}
	@Override
	public Drug getDrugById(Integer id, String locale) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		if(id == null) {
			throw new ServiceException("Empty id!");
		}
		if(locale.equals(null)) {
			throw new ServiceException("Empty locale!");
		}
		Drug drug = null;

		try {
			drug = drugDao.loadDrugById(id, locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drug;
	}
	@Override
	public void addDrug(Drug drug) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();

		try {
			drugDao.saveDrug(drug);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void editDrug(Drug drug) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();

		try {
			drugDao.alterDrug(drug);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void addDrugToCart(Integer drugId, Integer userId) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		try {
			drugDao.saveDrugToCart(drugId, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public List<Drug> getDrugsFromCart(Integer userId) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = null;
		try {
			drugList = drugDao.loadDrugsFromCart(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public void removeDrugFromCart(Integer drugId, Integer userId) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		try {
			drugDao.deleteDrugFromCart(drugId, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void changeCart(Cart cart) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		try {
			drugDao.updateDrugInCart(cart);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public List<Drug> getDrugsByRecipe(Integer needRecipe) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = null;

		try {
			drugList = drugDao.loadDrugsByRecipe(needRecipe);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
}
