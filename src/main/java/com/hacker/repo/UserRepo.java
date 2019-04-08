package com.hacker.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hacker.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public List<Object[]> findByEmail(@Param("email") String email);
	public List<Object[]> findByEmailID(@Param("email") String email);
}
