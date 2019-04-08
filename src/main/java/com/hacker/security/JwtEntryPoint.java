package com.hacker.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
	//private static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		//logger.error("Error message->{}", ex.getMessage());
		System.out.println("from entry"+ex.getMessage());
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

	}

}
