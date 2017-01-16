package com.finalproject.onlineapteka.dao;

import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface CartDao {
	void saveDrugToCart(Integer drugId, Integer userId) throws DAOException;
	List<Drug> loadDrugsFromCart(Integer userId) throws DAOException;
	void deleteDrugFromCart(Integer drugId, Integer userId) throws DAOException;
	void updateDrugInCart(Cart cart) throws DAOException;
	void deleteCart(Integer userId) throws DAOException;
	List<Drug> loadDrugsFromCart(Integer userId, String locale) throws DAOException;
}
