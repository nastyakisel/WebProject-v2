package com.finalproject.onlineapteka.service;

import java.util.List;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface UserService {
	
	User getUser(char[] userName, char[] password) throws ServiceException;
	Integer addUser(User user) throws ServiceException;
	User getUserById(Integer userId) throws ServiceException;
	List<User> getUsersByRole(Integer role) throws ServiceException;
}
