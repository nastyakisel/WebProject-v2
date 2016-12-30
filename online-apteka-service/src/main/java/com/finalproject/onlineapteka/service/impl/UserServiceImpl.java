package com.finalproject.onlineapteka.service.impl;


import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

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
		if (user != null) {
			String receivedPassword = user.getPassword();
			if (!password.equals(receivedPassword)) {
				user = null;
			}
		}
		return user;
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

		User receivedUser = null;
		try {
			receivedUser = userDao.loadUser(user.getUserName());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		if (receivedUser != null) {
			return 0;
		} else {
			try {
				userDao.saveUser(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return 1;
	}
}