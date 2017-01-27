package com.finalproject.onlineapteka.service.impl;

import java.util.ArrayList;
import java.util.List;

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
		List<Drug> drugList = new ArrayList<>();

		try {
			drugList = drugDao.loadAllDrugs();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public List<Drug> getAllDrugs(String locale) throws ServiceException {
		if (locale.isEmpty()) {
			throw new ServiceException("Empty locale!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		
		List<Drug> drugList = new ArrayList<>();

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
		if (categoryId == null) {
			throw new ServiceException("Empty categoryId!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = new ArrayList<>();

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
			throw new ServiceException("Empty categoryId!");
		}
		if(locale.equals(null)) {
			throw new ServiceException("Empty locale!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = new ArrayList<>();

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
		if(drugName.isEmpty()) {
			throw new ServiceException("Empty drugName!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = new ArrayList<>();

		try {
			drugList = drugDao.loadDrugsByName(drugName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public List<Drug> getDrugsByName(String drugName, String locale)
			throws ServiceException {
		if(drugName.isEmpty()) {
			throw new ServiceException("Empty drugName!");
		}
		if(locale.isEmpty()) {
			throw new ServiceException("Empty locale!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = new ArrayList<>();

		try {
			drugList = drugDao.loadDrugsByName(drugName, locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
	@Override
	public Drug getDrugById(Integer drugId) throws ServiceException {
		if(drugId == null) {
			throw new ServiceException("Empty drugId!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		
		Drug drug = null;

		try {
			drug = drugDao.loadDrugById(drugId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drug;
	}
	@Override
	public Drug getDrugById(Integer drugId, String locale) throws ServiceException {
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		if(drugId == null) {
			throw new ServiceException("Empty drugId!");
		}
		if(locale.isEmpty()) {
			throw new ServiceException("Empty locale!");
		}
		Drug drug = null;

		try {
			drug = drugDao.loadDrugById(drugId, locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drug;
	}
	@Override
	public Integer addDrug(Drug drug) throws ServiceException {
		if(drug == null) {
			throw new ServiceException("Empty drug!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		Integer drugId = null;
		try {
			drugId = drugDao.saveDrug(drug);
			return drugId;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void addDrug(Drug drug, String locale, Integer drugId) throws ServiceException {
		if(drug == null) {
			throw new ServiceException("Empty drug!");
		}
		if(locale.isEmpty()) {
			throw new ServiceException("Empty locale!");
		}
		if(drugId == null) {
			throw new ServiceException("Empty drugId!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		
		try {
			drugDao.saveDrug(drug, locale, drugId);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void editDrug(Drug drug) throws ServiceException {
		if(drug == null) {
			throw new ServiceException("Empty drug!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();

		try {
			drugDao.alterDrug(drug);;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void editDrug(Drug drug, String locale) throws ServiceException {
		if(drug == null) {
			throw new ServiceException("Empty drug!");
		}
		if(locale.isEmpty()) {
			throw new ServiceException("Empty locale!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();

		try {
			drugDao.alterDrug(drug, locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void removeDrug(Integer drugId) throws ServiceException {
		if(drugId == null) {
			throw new ServiceException("Empty drugId!");
		}
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();

		try {
			drugDao.deleteDrug(drugId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Drug> getDrugsByRecipe(Integer needRecipe, String locale) throws ServiceException {
		if(needRecipe == null || needRecipe > 1) {
			throw new ServiceException("Empty needRecipe!");
		}
		
		DrugDao drugDao = DAOFactoryImpl.getInstance().getGrugDao();
		List<Drug> drugList = new ArrayList<>();

		try {
			drugList = drugDao.loadDrugsByRecipe(needRecipe, locale);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drugList;
	}
}
