package com.alpha.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alpha.common.model.OrderBean;

/**
 * 订单
 * 
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface ROrderMapper {

	OrderBean getOrderById(@Param("orderId") String orderId);

	int insert(OrderBean bean);

	List<OrderBean> getOrderList(@Param("userName") String userName);
}
