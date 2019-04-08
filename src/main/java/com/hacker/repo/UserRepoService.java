package com.hacker.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hacker.model.User;
import com.hacker.model.UserPrinciple;

@Service
public class UserRepoService implements UserDetailsService{

	public UserRepoService() {
		// TODO Auto-generated constructor stub
	}
	@Autowired(required=true)
	private UserRepo userRepo;
	
	public List<User> findAll()
	{
		
		return userRepo.findAll();
	}
	
	public Optional<User> findUserById(int userId)
	{
		return userRepo.findById(userId);
	}
	
	public User registerUser(User user)
	{
		return userRepo.save(user);
	}
	
	public UserDetails findByEmailID(String email)
	{
		List<Object[]> userRec= userRepo.findByEmailID(email);
		User user=new User();
		
		if(userRec.size()>0)
		{
		Object[] userReceived=userRec.get(0);
		
		
		user.setEmail(String.valueOf(userReceived[0]));
		user.setPassword(String.valueOf(userReceived[1]));
		user.setUserName(String.valueOf(userReceived[2]));
		}
       return UserPrinciple.build(user);

	}
	
	public List<Object[]> findByEmail(String email)
	{
		return userRepo.findByEmail(email);
            	
	}
	
	public User save(User user)
	{
		User userN = userRepo.save(user);
		return userN;

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Object[]> userRec= userRepo.findByEmailID(email);
		Object[] userReceived=userRec.get(0);
		
		User user=new User();
		user.setEmail(String.valueOf(userReceived[0]));
		user.setPassword(String.valueOf(userReceived[1]));
		user.setUserName(String.valueOf(userReceived[2]));
		
		
		System.out.println("in loadUserByUsername"+user);

       return UserPrinciple.build(user);
	}
	
	
	

}
