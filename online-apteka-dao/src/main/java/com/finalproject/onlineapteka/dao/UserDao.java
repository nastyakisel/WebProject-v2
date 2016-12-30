package com.finalproject.onlineapteka.dao;


import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface UserDao {
	User loadUser(String userName) throws DAOException;
	void saveUser(User user) throws DAOException;
	
}
