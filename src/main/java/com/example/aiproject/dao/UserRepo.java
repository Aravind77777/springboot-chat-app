package com.example.aiproject.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aiproject.model.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
	
	 Optional<Users> findByEmail(String email);
	 
	 Optional<Users> findByPhoneNumber(String phoneNumber);
	 Optional<Users> findByUsername(String username);
	 
	 boolean existsByEmail(String email);
	 boolean existsByUsername(String username);

}
