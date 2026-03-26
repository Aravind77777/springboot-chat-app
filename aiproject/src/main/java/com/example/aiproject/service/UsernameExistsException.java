package com.example.aiproject.service;

public class UsernameExistsException extends RuntimeException {
	
	    private String username;
	    
	    public  UsernameExistsException (String username) {
	    	super("Username already exists");
	    	this.username=username;
	    }
	    
	    public String getUsername() {
	    	return username;
	    }

}
