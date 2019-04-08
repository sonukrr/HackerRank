package com.hacker.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hacker.Dao.UserDao;
import com.hacker.exception.HackerrankException;
import com.hacker.model.User;

@Service
public class UserService implements IUser {
	
	UserDao userDao=new UserDao();

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int registerUser(User user) throws HackerrankException {
		// TODO Auto-generated method stub
		return (userDao.registerUser(user));
	}

	@Override
	public boolean userCheck(User user) throws HackerrankException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return (userDao.userCheck(user));
	}
	
	@Override
	public boolean userCheckRegister(User user,Connection connObj) throws HackerrankException
	{
		return (userDao.userCheckRegister(user, connObj));
	}
	
	@Override
	public List<User> fetchAllUser() throws HackerrankException, UnsupportedEncodingException
	{
		return (userDao.fetchAllUser());
	}
	
	@Override
	public User fetchUserById(int userId) throws HackerrankException, UnsupportedEncodingException
	{
		return (userDao.fetchUserById(userId));
	}
	

}
