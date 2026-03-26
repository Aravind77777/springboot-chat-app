package com.example.aiproject.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.aiproject.dao.UserRepo;
import com.example.aiproject.dto.AuthResponseDTO;
import com.example.aiproject.dto.RegisterRequestDTO;
import com.example.aiproject.model.Role;
import com.example.aiproject.model.Users;

@Service
public class UserService {
	
	private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;


    public UserService(UserRepo userRepo,
                       PasswordEncoder passwordEncoder,
                       OtpService otpService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
    }

    // ✅ REGISTER
    public String register(RegisterRequestDTO request) {

        if (userRepo.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already exists");

        if (userRepo.existsByUsername(request.getUsername()))
        	throw new UsernameExistsException(request.getUsername());

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setVerified(false);

        // Base64 image
        user.setProfileImageBase64(request.getProfileImageBase64());

        Users saved = userRepo.save(user);

        otpService.generateOtp(saved, request.getOtpType());

        return "OTP sent successfully";
    }

    // ✅ VERIFY OTP
    public String verify(String email, String otp) {

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        otpService.verifyOtp(user, otp);

        return "Account verified successfully";
    }

    // ✅ LOGIN
    public AuthResponseDTO login(String email, String password) {

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid password");

        if (!user.isVerified())
            throw new RuntimeException("Account not verified");

        return new AuthResponseDTO("Login Success", user.getEmail());
    }
    
    public String resendOtp(String email, String type) {

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isVerified()) {
            throw new RuntimeException("Already verified");
        }

        otpService.generateOtp(user, type);

        return "OTP resent successfully";
    }
    
    public String changePassword(String email, String oldPassword, String newPassword) {

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        return "Password changed successfully";
    }
    
    public String forgotPassword(String email) {

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        otpService.generateOtp(user, "EMAIL");

        return "OTP sent to email";
    }
    
    public String resetPassword(String email, String otp, String newPassword) {

        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        otpService.verifyOtp(user, otp);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        return "Password reset successful";
    }

}
