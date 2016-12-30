package com.finalproject.onlineapteka.service;

import com.finalproject.onlineapteka.bean.User;
import com.finalproject.onlineapteka.service.exception.ServiceException;

public interface UserService {
	
	User getUser(String userName, String password) throws ServiceException;
	Integer addUser(User user) throws ServiceException;
}
