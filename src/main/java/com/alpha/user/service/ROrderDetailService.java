package com.alpha.user.service;

import java.util.List;

import com.alpha.common.base.BusException;
import com.alpha.common.model.OrderDetailBean;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface ROrderDetailService {

	public int insert(OrderDetailBean bean);

	public List<OrderDetailBean> getListByOrderIds(List<String> orderIds);

	public boolean buyGoods(String userName, List<Integer> goodsId, List<String> goodsName, List<Integer> goodsNum,
			List<String> goodsPrice) throws BusException;

	public List<OrderDetailBean> orderList(String userName);
}
