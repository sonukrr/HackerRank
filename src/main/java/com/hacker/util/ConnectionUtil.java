package com.hacker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hacker.exception.HackerrankException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws HackerrankException
	{
		Connection connObj = null;
		
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connObj=DriverManager.getConnection("jdbc:mysql://localhost/payroll","root","root");
		
		if(connObj==null)
			System.out.println("Not connected");
		else
			System.out.println("Connected");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new HackerrankException("Error in connection");
		}
		
		return connObj;
		
	}

}
