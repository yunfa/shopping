package com.alpha.transfer.dto;

/**
 * 转账dto
 * 
 * @author Li Yunfa
 * @date 2017年6月25日
 */
public class TransterDto {

	// 记录名
	private String recordName;

	// 转账金额
	private String perAmount;

	// 转账时间
	private Long dealTime;

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public String getPerAmount() {
		return perAmount;
	}

	public void setPerAmount(String perAmount) {
		this.perAmount = perAmount;
	}

	public Long getDealTime() {
		return dealTime;
	}

	public void setDealTime(Long dealTime) {
		this.dealTime = dealTime;
	}

}
