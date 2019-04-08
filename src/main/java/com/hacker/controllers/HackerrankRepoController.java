package com.hacker.controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.model.User;
import com.hacker.repo.UserRepoService;


@RestController
public class HackerrankRepoController {
	
	@Autowired(required=true)
	private UserRepoService userRepoService;

	public HackerrankRepoController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/alluser")
	public List<User> getAllUser()
	{
		List<User> list=new ArrayList<User>();
		list=userRepoService.findAll();
		return list;
	}
	
	@PostMapping("/saverepouser")
	public String saveEmployee(@RequestBody User user)
	{
		String msg="failure";
		
		if(userRepoService.findByEmail(user.getEmail())==null)
			{
				User updateUser=userRepoService.registerUser(user);
				if(updateUser==null)
				{
					msg="failure";
				}
				else
				{
					msg="success";
				}
				
			}
		else
		{
			msg="User exist";
		}
		return msg;
	}
	
	@PostMapping("/loginrepouser")
	public String loginCheckEmployee(@RequestBody User user)
	{
		String msg="failure";
		Object[] userReceived=userRepoService.findByEmail(user.getEmail()).get(0);
		String password=String.valueOf(userReceived[1]);
		System.out.println(password);
		
		if(password!=null)
		{
			if(password.equals(user.getPassword()))
			msg="success";
			else
			msg="password wrong";

		}
		else
		{
			msg="User doesnot exist";
		}
		return msg;
	}
	

}
