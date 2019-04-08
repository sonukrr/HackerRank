package com.hacker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hacker.model.JwtResponse;
import com.hacker.model.User;
import com.hacker.repo.UserRepoService;
import com.hacker.security.JwtGenerator;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRepoController {

	public AuthRepoController() {
		// TODO Auto-generated constructor stub
	}
    @Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    UserRepoService userRepoService;
 
 
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    JwtGenerator jwtGenerator;
    
    @GetMapping("/")
    public String test()
    {
    	
    	return "success";
    }
    @PostMapping("login")
    public  ResponseEntity<?> testLogin(@RequestBody User user)
    {
    	System.out.println(user+"ok");
    	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		user.getEmail(),
                        user.getPassword()
                )
        );
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
 
        String jwt = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
	
    
 
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser( @RequestBody User	user) {
  
    	System.out.println("------>"+user);
        if(userRepoService.findByEmailID(user.getEmail())==null) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
 
        // Creating user's account
        System.out.println(user);
       user.setPassword(encoder.encode(user.getPassword()));
        
       System.out.println("after encoding"+user);
        User userN = new User(user.getUserId(), user.getUserName(),
        		user.getEmail(), user.getPassword());
 

        userRepoService.save(userN);
 
        return ResponseEntity.ok().body("User registered successfully!");
    }
}


