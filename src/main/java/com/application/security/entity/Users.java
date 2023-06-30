package com.application.security.entity;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userId;
	
	@NotEmpty(message = "Name may not be empty")
	@Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
	private String userName;
	
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	@NotEmpty(message = "Email may not be empty")
	private String userEmail;
	
	@NotEmpty(message = "Password cannot be empty")
	private String userPassword;
	
	@NotNull(message = "Mobile Number cannot be empty")
	private Long userMobile;
	
	@NotEmpty(message = "Role cannot be empty")
	private String userRole;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

public Users(@NotNull(message = "ID may not be null") String userId,
			@NotEmpty(message = "Name may not be empty") @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long") String userName,
			@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}") @NotEmpty(message = "Email may not be empty") String userEmail,
			@NotEmpty(message = "Password cannot be empty") String userPassword,
			@NotEmpty(message = "Mobile Number cannot be empty") Long userMobile,
			@NotEmpty(message = "Role cannot be empty") String userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userMobile = userMobile;
		this.userRole = userRole;
	}



//	public Users(String userId, String userName, String userEmail, String userPassword, Long userMobile,
//			String userRole) {
//		super();
//		this.userId = userId;
//		this.userName = userName;
//		this.userEmail = userEmail;
//		this.userPassword = userPassword;
//		this.userMobile = userMobile;
//		this.userRole = userRole;
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Long getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(Long userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userMobile=" + userMobile + ", userRole=" + userRole + "]";
	}
	
	
	
	
	
	
	

}
