package com.hacker.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.List;

import com.hacker.exception.HackerrankException;
import com.hacker.model.User;

public interface IUser {
	
	public int registerUser(User user) throws HackerrankException;
	
	public boolean userCheck(User user) throws HackerrankException, UnsupportedEncodingException;
	
	public boolean userCheckRegister(User user,Connection connObj) throws HackerrankException;
	
	public List<User> fetchAllUser() throws HackerrankException, UnsupportedEncodingException;
	
	public User fetchUserById(int userId) throws HackerrankException, UnsupportedEncodingException;
	
}
