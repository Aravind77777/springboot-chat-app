package com.example.aiproject.model;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class Users {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 
	 /**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	 /**
 	 * @param id the id to set
 	 */
	 public void setId(long id) {
		 this.id = id;
	 }


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


	 /**
 	 * @return the chatHistory
 	 */
	 public List<ChatMessage> getChatHistory() {
		 return chatHistory;
	 }


	 /**
 	 * @param chatHistory the chatHistory to set
 	 */
	 public void setChatHistory(List<ChatMessage> chatHistory) {
		 this.chatHistory = chatHistory;
	 }


	 @Column(nullable = false, unique = true )
	 private String username;
	 
	 @Column(nullable = false, unique = true )
	 private String email;
	 
	 @Column(nullable = false, unique = true )
	 private String phoneNumber;
	 
	 private String password;
	 
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
	 * @return the verified
	 */
	public boolean isVerified() {
		return verified;
	}


	 /**
 	 * @param verified the verified to set
 	 */
	 public void setVerified(boolean verified) {
		 this.verified = verified;
	 }


	 @Column(name = "is_verified")
	 private boolean verified = false;
	 
	 
	 @Lob
	 @Column(columnDefinition = "LONGTEXT")
	 private String profileImageBase64;
	 
	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	 private List<ChatMessage> chatHistory;
	 
	 @Enumerated(EnumType.STRING)
	  private Role role = Role.USER;
	 
	 
	 /**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}


	 /**
 	 * @param role the role to set
 	 */
	 public void setRole(Role role) {
		 this.role = role;
	 }


	 public Users() {}
	 
	 
	 public Users(long id, String username, String email , String phoneNumber, String profileImageBase64, Role role,String password) {
		 this.id=id;
		 this.username=username;
		 this.email=email;
		 this.phoneNumber=phoneNumber;
		 this.profileImageBase64=profileImageBase64;
		 this.role=role;
		 this.password=password;
	 }
	 
	 
}
