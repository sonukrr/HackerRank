package com.hacker.model;

import java.util.Collection;
import java.util.Objects;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;
 
	private int id;
 
    private String username;
 
    private String email;
 
    @JsonIgnore
    private String password;
 
 
    public UserPrinciple(int id, 
			    		String username, String email, String password
			    		) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;

    }
 
    public static UserPrinciple build(User user) {
 
 
        return new UserPrinciple(
        		user.getUserId(),
        		user.getUserName(),
                user.getEmail(),
                user.getPassword()

        );
    }
 
    @Override
	public String toString() {
		return "UserPrinciple [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}

	public int getId() {
        return id;
    }
 
    public String getName() {
        return this.username;
    }
 
    public String getEmail() {
        return this.email;
    }
 
    @Override
    public String getUsername() {
        return username;
    }
 
    @Override
    public String getPassword() {
        return password;
    }
 
 
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
