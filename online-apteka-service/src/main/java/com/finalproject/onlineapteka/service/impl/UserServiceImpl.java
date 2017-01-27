package com.finalproject.onlineapteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.dao.UserDao;
import com.finalproject.onlineapteka.dao.exception.DAOException;
import com.finalproject.onlineapteka.dao.factories.DAOFactoryImpl;
import com.finalproject.onlineapteka.service.UserService;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private static final Integer USER_NOT_ADDED = 0;

	@Override
	public User getUser(char[] userName, char[] password)
			throws ServiceException {
		UserDao userDao = DAOFactoryImpl.getInstance().getUserDao();

		if (userName.length == 0) {
			throw new ServiceException("Empty name");
		}
		if (password.length == 0) {
			throw new ServiceException("Empty password");
		}
		User user = null;
		String userNameCipher = DigestUtils.md5Hex(String.valueOf(userName));
		try {
			user = userDao.loadUser(userNameCipher);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		if (user != null) {
			String userPasswordCipher = DigestUtils.md5Hex(String.valueOf(password));
			if (user.getPassword().equals(userPasswordCipher)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public Integer addUser(User user) throws ServiceException {
		UserDao userDao = DAOFactoryImpl.getInstance().getUserDao();
		if (user == null) {
			throw new ServiceException("Empty user!");
		}
		
		User receivedUser = null;
		Integer userId = 0;
		try {
			receivedUser = userDao.loadUser(user.getUserName());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		if (receivedUser != null) {
			return USER_NOT_ADDED;
		} else {
			try {
				String userNameCipher = DigestUtils.md5Hex(user.getUserName());
				String userPasswordCipher = DigestUtils.md5Hex(user.getPassword());
				user.setUserName(userNameCipher);
				user.setPassword(userPasswordCipher);
				userId = userDao.saveUser(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return userId;
	}

	@Override
	public User getUserById(Integer userId) throws ServiceException {
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

		List<User> userList = new ArrayList<>();
		try {
			userList = userDao.loadUsersByRole(role);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return userList;
	}
}