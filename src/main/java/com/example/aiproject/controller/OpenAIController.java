package com.example.aiproject.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aiproject.dto.ChatHistoryDTO;
import com.example.aiproject.dto.ChatRequestDTO;

import com.example.aiproject.service.OpenAIService;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class OpenAIController {
	
	

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping
    public String chat(@RequestParam String email,
            @RequestBody ChatRequestDTO request) {

return openAIService.chat(email, request.message());
}

    @GetMapping("/history")
    public List<ChatHistoryDTO> history(@RequestParam String email) {

        return openAIService.history(email)
                .stream()
                .map(c -> new ChatHistoryDTO(
                        c.getUserMessage(),
                        c.getAiResponse(),
                        c.getCreatedAt()
                ))
                .toList();
    }
	
}
