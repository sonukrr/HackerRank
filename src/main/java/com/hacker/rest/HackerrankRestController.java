package com.hacker.rest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.exception.HackerrankException;
import com.hacker.model.User;
import com.hacker.service.UserService;

@RequestMapping("/hacker")
@RestController
public class HackerrankRestController {

	public HackerrankRestController() {
		// TODO Auto-generated constructor stub
	}
	

		
		@Autowired
		private UserService userService;


		
		@GetMapping("/user")
		public List<User> getAllEmp()
		{
			List<User> userList=new ArrayList<User>();
			
			try {
				userList=userService.fetchAllUser();
			} catch (HackerrankException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userList;
			
		}
		
		@GetMapping("/user/{userId}")
		public User getAllEmpByID(@PathVariable int userId)
		{
			
			User emp=new User();
			
			try {
				emp=userService.fetchUserById(userId);
			} catch (HackerrankException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return emp;
			
		}
	}



