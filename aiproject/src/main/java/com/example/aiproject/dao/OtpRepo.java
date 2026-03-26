package com.example.aiproject.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aiproject.model.*;

public interface OtpRepo extends JpaRepository<Otp, Long> {
	
	Optional<Otp> findByUser(Users user);

}
