package com.alpha.common.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;

public class DsUserBean {

	private Integer userId;

	private String userName;

	private String userPwd1;

	private String userPwd3;

	private BigDecimal amount;

	private String userFlag;

	private String dbName;

	private String token;

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

	public String getUserPwd1() {
		return userPwd1;
	}

	public void setUserPwd1(String userPwd1) {
		this.userPwd1 = userPwd1;
	}

	public String getUserPwd3() {
		return userPwd3;
	}

	public void setUserPwd3(String userPwd3) {
		this.userPwd3 = userPwd3;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
