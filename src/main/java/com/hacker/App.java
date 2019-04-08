package com.hacker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.hacker.controllers.AuthRepoController;
import com.hacker.controllers.HackerrankController;
import com.hacker.controllers.HackerrankRepoController;
import com.hacker.repo.UserRepoService;
import com.hacker.rest.HackerrankRestController;
import com.hacker.security.JwtGenerator;
import com.hacker.service.UserService;

/*@Configuration
@ComponentScan("{com.payroll}")
@EnableWebMvc
@EnableAutoConfiguration*/


@EntityScan("com.hacker.*")
@EnableJpaRepositories(value="com.hacker.repo")
@SpringBootApplication
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}
	
	
/*	@Bean
	public AuthRepoController authRepoController()
	{
		return new AuthRepoController();
	}
	@Bean
	public JwtGenerator jwtGenerator()
	{
		return new JwtGenerator();
	}
	
	
	@Bean
	public HackerrankController testController()
	{
		return new HackerrankController();
	}
	
	@Bean
	public HackerrankRestController restController()
	{
		return new HackerrankRestController();
	}
	
	@Bean
	public HackerrankRepoController repoController()
	{
		return new HackerrankRepoController();
	}
	
	@Bean
	public UserRepoService userRepoService() {
		return  new UserRepoService();
	}
	
	
	@Bean
	public UserService getLoginService() {
		return  new UserService();
	}
	
	*/
	
	@Bean
	public UrlBasedViewResolver setUpViewResolver()
	{
		UrlBasedViewResolver resolver=new UrlBasedViewResolver();
		
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		
		return resolver;
	}
	
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(App.class, args);
	}

}
