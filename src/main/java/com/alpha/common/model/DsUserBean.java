package com.alpha.common.model;

import java.math.BigDecimal;

public class DsUserBean {

	private Integer userId;

	private String userNumber;

	private String userPwd1;

	private String userPwd3;

	private BigDecimal amount;

	private String userFlag;

	private String dbName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
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

}
