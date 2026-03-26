package com.example.aiproject.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aiproject.model.*;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {
	
	 List<ChatMessage> findByUserOrderByCreatedAtDesc(Users user);

}
