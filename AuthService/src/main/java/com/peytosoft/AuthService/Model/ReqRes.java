package com.peytosoft.AuthService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
	
	    private int statusCode;
	    private String error;
	    private String message;
	    private String token;
	    private String refreshToken;
	    private String expirationTime;
	    private String name;
	    private String email;
	    private String role;
	    private String password;
	    private User ourUsers;
	    
	    
	    public ReqRes() {}
	    
		public ReqRes(int statusCode, String error, String message, String token, String refreshToken,
				String expirationTime, String name, String email, String role, String password, User ourUsers) {
			super();
			this.statusCode = statusCode;
			this.error = error;
			this.message = message;
			this.token = token;
			this.refreshToken = refreshToken;
			this.expirationTime = expirationTime;
			this.name = name;
			this.email = email;
			this.role = role;
			this.password = password;
			this.ourUsers = ourUsers;
		}
		public int getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getRefreshToken() {
			return refreshToken;
		}
		public void setRefreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
		}
		public String getExpirationTime() {
			return expirationTime;
		}
		public void setExpirationTime(String expirationTime) {
			this.expirationTime = expirationTime;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public User getOurUsers() {
			return ourUsers;
		}
		public void setOurUsers(User ourUsers) {
			this.ourUsers = ourUsers;
		}
	    
	    
	    
	    

}
