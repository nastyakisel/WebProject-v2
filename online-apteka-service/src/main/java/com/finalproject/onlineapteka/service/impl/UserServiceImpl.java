package com.finalproject.onlineapteka.service.impl;


import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
	
	private static final Integer USER_ADDED = 1;
	
	private static final Integer USER_NOT_ADDED = 0;
	
	@Override
	public User getUser(String userName, String password)
			throws ServiceException {
		UserDao userDao = DAOFactoryImpl.getInstance().getUserDao();

		if (userName.isEmpty()) {
			throw new ServiceException("Empty name");
		}
		if (password.isEmpty()) {
			throw new ServiceException("Empty password");
		}
		User user = null;
		try {
			user = userDao.loadUser(userName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		if(user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public Integer addUser(User user) throws ServiceException {
		UserDao userDao = DAOFactoryImpl.getInstance().getUserDao();

		if (user.getUserName().isEmpty()) {
			throw new ServiceException("Empty name");
		}
		if (user.getPassword().isEmpty()) {
			throw new ServiceException("Empty password");
		}
		if (user.getFirstName().isEmpty()) {
			throw new ServiceException("Empty password");
		}
		if (user.getSecondName().isEmpty()) {
			throw new ServiceException("Empty password");
		}

		User receivedUser = null;
		try {
			receivedUser = userDao.loadUser(user.getUserName());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		if (receivedUser != null) {
			return USER_NOT_ADDED;
		} else {
			try {
				userDao.saveUser(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return USER_ADDED;
	}
	@Override
	public User getUserById(Integer userId)
			throws ServiceException {
		UserDao userDao = DAOFactoryImpl.getInstance().getUserDao();

		if (userId == 0) {
			throw new ServiceException("Empty userId");
		}
		
		User user = null;
		try {
			user = userDao.loadUserById(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return user;
	}
	
	@Override
	public List<User> getUsersByRole(Integer role) throws ServiceException {
		UserDao userDao = DAOFactoryImpl.getInstance().getUserDao();

		if (role == 0) {
			throw new ServiceException("Empty userId");
		}
		
		List<User> userList = new ArrayList<User>();
		try {
			userList = userDao.loadUsersByRole(role);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return userList;
	}
}