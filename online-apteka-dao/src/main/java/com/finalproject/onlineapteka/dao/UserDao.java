package com.finalproject.onlineapteka.dao;


import java.util.List;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.exception.DAOException;

public interface UserDao {
	User loadUser(String userName) throws DAOException;
	Integer saveUser(User user) throws DAOException;
	User loadUserById(Integer userId) throws DAOException;
	List<User> loadUsersByRole(Integer role) throws DAOException;
	
}
