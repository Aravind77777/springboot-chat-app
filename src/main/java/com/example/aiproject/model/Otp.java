package com.example.aiproject.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "otp")
public class Otp {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   
	   /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	   /**
 	   * @param id the id to set
 	   */
	   public void setId(Long id) {
		   this.id = id;
	   }

	   /**
 	   * @return the code
 	   */
	   public String getCode() {
		   return code;
	   }

	   /**
 	   * @param code the code to set
 	   */
	   public void setCode(String code) {
		   this.code = code;
	   }

	   /**
 	   * @return the expiry
 	   */
	   public LocalDateTime getExpiry() {
		   return expiry;
	   }

	   /**
 	   * @param expiry the expiry to set
 	   */
	   public void setExpiry(LocalDateTime expiry) {
		   this.expiry = expiry;
	   }

	   /**
 	   * @return the user
 	   */
	   public Users getUser() {
		   return user;
	   }

	   /**
 	   * @param user the user to set
 	   */
	   public void setUser(Users user) {
		   this.user = user;
	   }

	   private String code;
	   
	   private LocalDateTime expiry;
	   
	   /**
	 * @return the lastSentAt
	 */
	public LocalDateTime getLastSentAt() {
		return lastSentAt;
	}

	   /**
 	   * @param lastSentAt the lastSentAt to set
 	   */
	   public void setLastSentAt(LocalDateTime lastSentAt) {
		   this.lastSentAt = lastSentAt;
	   }

	   private LocalDateTime lastSentAt;
	   
	   
	   @OneToOne
	   @JoinColumn(name = "user_id", unique = true, nullable = false)
	   private Users user;
	   
	   public Otp() {
	   }
	   
	   public Otp(Users user, String code, LocalDateTime expiry) {
	        this.user = user;
	        this.code = code;
	        this.expiry = expiry;
	        this.lastSentAt = LocalDateTime.now();
	    }
	   
	   
	   
	   
}
