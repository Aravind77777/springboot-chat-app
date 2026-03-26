package com.example.aiproject.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.aiproject.service.UsernameExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
	
	@ExceptionHandler(UsernameExistsException.class)
	public ResponseEntity<Map<String, Object>> handleUsernameExists(UsernameExistsException ex) {
		     String username = ex.getUsername();
		     
		     List<String> suggestions = new ArrayList<>();
		        suggestions.add(username + "123");
		        suggestions.add(username + "_01");
		        suggestions.add(username + "_dev");
		        suggestions.add(username + "_official");
		        suggestions.add(username + new Random().nextInt(999));

		        Map<String, Object> error = new HashMap<>();
		        error.put("message", "Username already exists");
		        error.put("suggestions", suggestions);

		        return ResponseEntity.badRequest().body(error);
	}
	
	

}
