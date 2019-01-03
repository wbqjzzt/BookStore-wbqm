package com.wbqm.module;

@SuppressWarnings("unused")
public class User {

	private int id;
	private int userIntegral;
	
	private String email;
	private String nickName;
	private String password;
	private String emailVerifyCode;
	private String lastLoginIp;
	
	private long lastLoginTime;
	private boolean emailVerify; 
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserIntegral() {
		return this.userIntegral;
	}
	
	public void setUserIntegral(int userIntegral) {
		this.userIntegral = userIntegral;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmailVerifyCode() {
		return this.emailVerifyCode;
	}
	
	public void setEmailVerifyCode(String verifyCode) {
		this.emailVerifyCode = verifyCode;
	}
	
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(String ip) {
		this.lastLoginIp = ip;
	}
	
	public long getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(long time) {
		this.lastLoginTime = time;
	}
	
	public boolean getEmailVerified() {
		return this.emailVerify;
	}
	
	public void setEmailVerified(boolean verify) {
		this.emailVerify = verify;
	}
}
