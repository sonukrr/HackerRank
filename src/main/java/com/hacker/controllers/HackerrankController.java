package com.hacker.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hacker.Dao.UserDao;
import com.hacker.exception.HackerrankException;
import com.hacker.model.User;
import com.hacker.service.UserService;

@Controller
public class HackerrankController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/loginM")
	public String login()
	{
		return "HackerrankLogin";
		
	}
	
	@GetMapping("/signUp")
	public String registerHacker()
	{
		return "HackerrankSignUp";
		
	}
	
	@GetMapping("/homeHacker")
	public String homeHacker()
	{
		return("Home");
	}
	
	@PostMapping("/RegisterUser")
	public ModelAndView registerUser(User user,ModelAndView mav)
	{
		String view="";
		
		try {
			int generatedID=userService.registerUser(user);
			
			if(generatedID!=0)
			{
				view="HackerrankLogin";
				mav=new ModelAndView(view);
				mav.addObject("message", "Successfully register with userId "+generatedID+".please login.");
			}
			else
			{
				view="HackerrankSignUp";
				mav=new ModelAndView(view);
				mav.addObject("message", "Cannot register user.Please try again");
			}
			
			
		} catch (HackerrankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
		
	}
	
	@PostMapping("/loginUser")
	public ModelAndView login(User user,ModelAndView mav )
	{
		System.out.println(user);
		
		String view="";
		try {
			boolean check=userService.userCheck(user);
			if(check)
			{
				view="Home";
				mav=new ModelAndView(view);
				mav.addObject("message", "Successfully Logged in.Welcome to Hackerrank.");
				mav.addObject("uId",1);
				mav.addObject("uName",user.getUserName());
			}
			else
			{
				view="HackerrankLogin";
				mav=new ModelAndView(view);
				mav.addObject("message", "Sorry username or password incorrect.");
			}
		} catch (HackerrankException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
		
	}
	
	@PostMapping("/imageUpload")
	public ModelAndView imageUpload(@RequestParam String url,ModelAndView mav )
	{

		
		UserDao userDao=new UserDao();
		System.out.println(url);
		
		try {
			userDao.registerImage(url,1);
			userDao.retriveImage();
			mav=new ModelAndView("Home");
			
		} catch (HackerrankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return mav;
	}
	
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session,ModelAndView mav)
	{
		session.invalidate();
		mav=new ModelAndView("HackerrankLogin");
		return mav;
		
	}
}
