package com.example.aiproject.dto;

import java.time.LocalDateTime;

public record ChatHistoryDTO(
		String userMessage,
		String aiResponse,
		LocalDateTime createdAt) {

}
