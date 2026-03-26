package com.example.aiproject.dto;

public class AuthResponseDTO {
	
	 private String message;
	    private String email;

	    public AuthResponseDTO(String message, String email) {
	        this.message = message;
	        this.email = email;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public String getEmail() {
	        return email;
	    }

}
