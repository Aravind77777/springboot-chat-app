package com.example.aiproject.dto;

public class RegisterRequestDTO {
	
	    /**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the otpType
	 */
	public String getOtpType() {
		return otpType;
	}
	/**
	 * @param otpType the otpType to set
	 */
	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}
	/**
	 * @return the profileImageBase64
	 */
	public String getProfileImageBase64() {
		return profileImageBase64;
	}
	/**
	 * @param profileImageBase64 the profileImageBase64 to set
	 */
	public void setProfileImageBase64(String profileImageBase64) {
		this.profileImageBase64 = profileImageBase64;
	}
		private String username;
	    private String email;
	    private String phoneNumber;
	    private String password;
	    private String otpType; // EMAIL or PHONE
	    private String profileImageBase64;

}
