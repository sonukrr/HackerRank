package com.hacker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component

@Entity(name="user")
@NamedNativeQueries(
{@NamedNativeQuery(name="User.findByEmail",query=" SELECT u.email,u.password,u.name FROM user u WHERE u.email =?"),
@NamedNativeQuery(name="User.findByEmailID",query="SELECT u.email,u.password,u.name FROM user u WHERE u.email =?")})
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int userId;
	
	
	@Column(name="name")
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	

	public User() {
		// TODO Auto-generated constructor stub
	}


	public User(int userId, String userName, String email, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}


	public User(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}


	public User(int userId, String userName, String email) {
		this.userName = userName;
		this.email = email;
		this.userId = userId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User:[userId:" + userId + ", userName:" + userName + ", email:" + email + ", password:" + password
				+ "]";
	}
	
	

}
