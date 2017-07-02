package com.alpha.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.common.dao.ROrderMapper;
import com.alpha.common.model.OrderBean;
import com.alpha.core.mybatis.BaseMybatisDao;
import com.alpha.user.service.ROrderService;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Service
public class ROrderServiceImpl extends BaseMybatisDao<ROrderMapper> implements ROrderService {

	@Autowired
	private ROrderMapper rOrderMapper;

	public OrderBean getOrderById(String orderId) {
		return rOrderMapper.getOrderById(orderId);
	}

	public int insert(OrderBean bean) {
		return rOrderMapper.insert(bean);
	}

	public List<OrderBean> getOrderList(String userName) {
		return rOrderMapper.getOrderList(userName);
	}
}
