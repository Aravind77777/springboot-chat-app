package com.example.aiproject.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.aiproject.model.*;

import com.example.aiproject.dao.ChatMessageRepo;
import com.example.aiproject.dao.UserRepo;

import java.time.LocalDateTime;
import java.util.*;



@Service
public class OpenAIService {
    private final UserRepo userRepo;
    private final ChatMessageRepo chatMessageRepo;
    private final RestTemplate restTemplate = new RestTemplate();

    public OpenAIService(UserRepo userRepo, ChatMessageRepo chatMessageRepo) {
        this.userRepo = userRepo;
        this.chatMessageRepo = chatMessageRepo;
    }

    public String chat(String email, String message) {

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String url = "http://localhost:11434/api/generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> options = new HashMap<>();
        options.put("temperature", 0.2);
        options.put("num_predict", 150);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "phi3");
        body.put("prompt", message);
        body.put("stream", false);
        body.put("options", options);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, request, Map.class);

        Map responseBody = response.getBody();

        String aiResponse =
                responseBody != null
                        ? responseBody.get("response").toString()
                        : "No AI response";

        ChatMessage chat = new ChatMessage();
        chat.setUser(user);
        chat.setUserMessage(message);
        chat.setAiResponse(aiResponse);
        chat.setCreatedAt(LocalDateTime.now());

        chatMessageRepo.save(chat);

        return aiResponse;
    }

    public List<ChatMessage> history(String email) {

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return chatMessageRepo.findByUserOrderByCreatedAtDesc(user);
    }



}
