package com.example.aiproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aiproject.dao.ResetPasswordDTO;
import com.example.aiproject.dto.AuthResponseDTO;
import com.example.aiproject.dto.ChangePasswordDTO;
import com.example.aiproject.dto.LoginRequestDTO;
import com.example.aiproject.dto.RegisterRequestDTO;

import com.example.aiproject.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
	
	 private final UserService userService;

	    public UserController (UserService userService) {
	        this.userService = userService;
	    }

	    
	    @PostMapping("/register")
	    public Map<String,String> register(@RequestBody RegisterRequestDTO request) {

	        String message = userService.register(request);

	        Map<String,String> res = new HashMap<>();
	        res.put("message", message);

	        return res;
	    }
	    
	    @PostMapping("/verify")
	    public String verify(@RequestParam String email,
	                         @RequestParam String otp) {
	        return userService.verify(email, otp);
	    }

	  
	    @PostMapping("/login")
	    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {

	        AuthResponseDTO response = userService.login(
	                request.getEmail(),
	                request.getPassword()
	        );

	        return ResponseEntity.ok(response);
	    }
	    
	    @PostMapping("/resend-otp")
	    public String resendOtp(@RequestParam String email,
	                            @RequestParam String type) {

	        return userService.resendOtp(email, type);
	    }
	    
	    @PostMapping("/change-password")
	    public String changePassword(@RequestBody ChangePasswordDTO request) {
	        return userService.changePassword(
	                request.getEmail(),
	                request.getOldPassword(),
	                request.getNewPassword()
	        );
	    }
	    
	    @PostMapping("/forgot-password")
	    public String forgotPassword(@RequestParam String email) {
	        return userService.forgotPassword(email);
	    }
	    
	    @PostMapping("/reset-password")
	    public String resetPassword(@RequestBody ResetPasswordDTO request) {

	        return userService.resetPassword(
	                request.getEmail(),
	                request.getOtp(),
	                request.getNewPassword()
	        );
	    }
}
