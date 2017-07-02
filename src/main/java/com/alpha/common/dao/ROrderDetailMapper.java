package com.alpha.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alpha.common.model.OrderDetailBean;

/**
 * 订单详情
 * 
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface ROrderDetailMapper {

	List<OrderDetailBean> getListByOrderId(@Param("orderId") String orderId);

	List<OrderDetailBean> getListByOrderIds(List<String> orderIds);

	int insert(OrderDetailBean bean);

}
