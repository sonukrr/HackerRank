package com.hacker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hacker.repo.UserRepoService;

@Configuration
@ComponentScan("com")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	JwtEntryPoint errorHandler;
	
	@Autowired
	UserRepoService userRepoService;
	
	@Bean
	public JwtFilter jwtFilter(){
		return new JwtFilter();
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable().
	                authorizeRequests()
	                .antMatchers("/api/auth/**").permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .exceptionHandling().authenticationEntryPoint(errorHandler).and()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        
	        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	    }
	
	
	    @Override
	    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder
	                .userDetailsService(userRepoService)
	                .passwordEncoder(passwordEncoder());
	    }


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}



}

