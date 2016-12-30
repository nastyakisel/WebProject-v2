package com.finalproject.onlineapteka.dao;
import java.sql.SQLException;
import java.util.List;

import com.finalproject.onlineapteka.bean.Cart;
import com.finalproject.onlineapteka.bean.Category;
import com.finalproject.onlineapteka.bean.Drug;
import com.finalproject.onlineapteka.dao.exception.DAOException;


public interface GoodsDao {
	List<Drug> loadAllGoods() throws DAOException;
	List<Drug> loadGoodsByCategory(Integer categoryId) throws DAOException;
	void saveGood(Drug drug) throws DAOException;
	Drug loadGoodById(Integer id) throws DAOException;
	void alterGood(Drug drug) throws DAOException;
	void saveDrugToCart(Integer drugId, Integer userId) throws DAOException;
	List<Drug> loadDrugsFromCart(Integer userId) throws DAOException;
	void deleteDrugFromCart(Integer drugId, Integer userId) throws DAOException;
	void updateDrugInCart(Cart cart) throws DAOException;
}
