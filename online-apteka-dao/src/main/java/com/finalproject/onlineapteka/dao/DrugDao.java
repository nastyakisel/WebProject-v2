package com.finalproject.onlineapteka.dao;

import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface DrugDao {
	List<Drug> loadAllDrugs() throws DAOException;
	List<Drug> loadDrugsByCategory(Integer categoryId) throws DAOException;
	void saveDrug(Drug drug) throws DAOException;
	Drug loadDrugById(Integer id) throws DAOException;
	void alterDrug(Drug drug) throws DAOException;
	void saveDrugToCart(Integer drugId, Integer userId) throws DAOException;
	List<Drug> loadDrugsFromCart(Integer userId) throws DAOException;
	void deleteDrugFromCart(Integer drugId, Integer userId) throws DAOException;
	void updateDrugInCart(Cart cart) throws DAOException;
	List<Drug> loadDrugsByRecipe(Integer needRecipe) throws DAOException;
	List<Drug> loadDrugsByName(String drugName) throws DAOException;
	List<Drug> loadAllDrugs(String locale) throws DAOException;
	Drug loadDrugById(Integer id, String locale) throws DAOException;
	List<Drug> loadDrugsByCategory(Integer categoryId, String locale) throws DAOException;
	List<Drug> loadDrugsByName(String drugName, String locale) throws DAOException;
}
