package com.example.aiproject.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.aiproject.dao.OtpRepo;
import com.example.aiproject.dao.UserRepo;
import com.example.aiproject.model.Otp;
import com.example.aiproject.model.Users;

@Service
public class OtpService {
	
	private final OtpRepo otpRepo;
	private final EmailService emailService;
	private final SmsService smsService;
	private final UserRepo userRepo;
	
	public OtpService(OtpRepo otpRepo, EmailService emailService, SmsService smsService, UserRepo userRepo ) {
		this.otpRepo=otpRepo;
		this.emailService=emailService;
		this.smsService=smsService;
		this.userRepo=userRepo;
	}
	
	public void generateOtp(Users user, String type) {

	    Optional<Otp> existingOtp = otpRepo.findByUser(user);

	  
	    if (existingOtp.isPresent()) {
	        Otp oldOtp = existingOtp.get();

	        if (oldOtp.getLastSentAt() != null &&
	            oldOtp.getLastSentAt().plusSeconds(30).isAfter(LocalDateTime.now())) {
	            throw new RuntimeException("Wait 30 seconds before requesting OTP again");
	        }

	        otpRepo.delete(oldOtp);
	    }

	    String code = String.valueOf(new Random().nextInt(900000) + 100000);

	    Otp otp = new Otp(user, code, LocalDateTime.now().plusMinutes(5));
	    otp.setLastSentAt(LocalDateTime.now());

	    otpRepo.save(otp);

	    if ("EMAIL".equalsIgnoreCase(type)) {
	        emailService.sendOtp(user.getEmail(), code);
	    } else if ("PHONE".equalsIgnoreCase(type)) {
	        smsService.sendOtp(user.getPhoneNumber(), code);
	    } else {
	        throw new RuntimeException("Invalid OTP type");
	    }
	}

	    public void verifyOtp(Users user, String input) {

	        Otp otp = otpRepo.findByUser(user)
	                .orElseThrow(() -> new RuntimeException("OTP not found"));

	        if (otp.getExpiry().isBefore(LocalDateTime.now())) {
	            otpRepo.delete(otp);
	            throw new RuntimeException("OTP expired");
	        }

	        if (!otp.getCode().equals(input)) {
	            throw new RuntimeException("Invalid OTP");
	        }

	        user.setVerified(true);
	        userRepo.save(user);

	        otpRepo.delete(otp);
	    }

	
}
