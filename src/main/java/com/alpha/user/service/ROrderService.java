package com.alpha.user.service;

import java.util.List;

import com.alpha.common.model.OrderBean;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface ROrderService {

	OrderBean getOrderById(String orderId);

	int insert(OrderBean bean);

	List<OrderBean> getOrderList(String userName);
}
