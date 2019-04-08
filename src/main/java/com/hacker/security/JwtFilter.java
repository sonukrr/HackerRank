package com.hacker.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hacker.repo.UserRepoService;

public class JwtFilter extends OncePerRequestFilter {
	
	//private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
	@Autowired
	JwtGenerator jwtGenerator;
	@Autowired
	UserRepoService userRepoService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//logger.info("In filter");
		try{
		String jwt = getJwt(request);
		
		System.out.println(jwt);
		if(jwt != null && jwtGenerator.validateToken(jwt)){

			String email = jwtGenerator.getUsernameFromToken(jwt);
			System.out.println(email);

			UserDetails userDetails = (UserDetails)userRepoService.findByEmailID(email);
			
			System.out.println("After checking"+userDetails);

			UsernamePasswordAuthenticationToken authToken = new
					UsernamePasswordAuthenticationToken(
							userDetails, null, null);
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			}
		}
		catch(Exception ex){
			//logger.error("Cannot authenticate user->{}", ex);
			
			System.out.println(ex.getMessage());
		}
		filterChain.doFilter(request, response);
	}

	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer "))
			return authHeader.replace("Bearer ", "");
		return null;
	}

}