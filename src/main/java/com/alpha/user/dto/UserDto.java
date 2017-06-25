package com.alpha.user.dto;

/**
 * App用户dto
 * 
 * @author Li Yunfa
 * @date 2017年6月25日
 */
public class UserDto {

	// 登录后的请求都要带上userId
	private Integer userId;

	// 用户名
	private String userName;

	// 登录后的请求都要带上token，以校验用户登录
	private String token;

	// rh账户金额
	private String rhAccount;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRhAccount() {
		return rhAccount;
	}

	public void setRhAccount(String rhAccount) {
		this.rhAccount = rhAccount;
	}

}
