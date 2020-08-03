package com.dhanush.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanush.airline.dao.UserDao;
import com.dhanush.airline.entity.User;

@Service
public class UserService {	
	
	@Autowired
	UserDao userDao;
	
	public void registerForm(User user) {
		userDao.save(user);
	}
	
	
	public User findByUserName(String userName){
		return userDao.findByUserName(userName);
	}

}
