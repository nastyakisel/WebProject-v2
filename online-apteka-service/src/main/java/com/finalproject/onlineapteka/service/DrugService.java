package com.finalproject.onlineapteka.service;

import java.util.List;

import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface DrugService {
	List<Drug> getAllDrugs() throws ServiceException;
	List<Drug> getGrugsByCategory(Integer categoryId) throws ServiceException;
	void addDrug(Drug drug) throws ServiceException;
	Drug getDrugById(Integer id) throws ServiceException;
	void editDrug(Drug drug) throws ServiceException;
	List<Drug> getDrugsByRecipe(Integer needRecipe) throws ServiceException;
	List<Drug> getDrugsByName(String drugName) throws ServiceException;
	List<Drug> getAllDrugs(String locale) throws ServiceException;
	Drug getDrugById(Integer id, String locale) throws ServiceException;
	List<Drug> getGrugsByCategory(Integer categoryId, String locale) throws ServiceException;
}
