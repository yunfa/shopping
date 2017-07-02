package com.alpha.order.dto;

/**
 * 订单dto
 * 
 * @author Li Yunfa
 * @date 2017年6月25日
 */
public class OrderDetailDto {

	// 订单编号
	private String orderId;

	// 商品名称
	private String goodsName;

	// 商品单价
	private String goodsPrice;

	// 商品数量
	private Integer goodsNum;

	// 订单时间
	private Long orderTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Long orderTime) {
		this.orderTime = orderTime;
	}

}
