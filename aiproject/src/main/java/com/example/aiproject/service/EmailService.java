package com.example.aiproject.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private final  JavaMailSender sender;
	
	public EmailService(JavaMailSender sender) {
		this.sender=sender;
	}
	
	public void sendOtp(String email, String otp) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Wellcome To Black AI");
		msg.setText("Your Verification code:  " + otp);
		sender.send(msg);
		
	}

}
