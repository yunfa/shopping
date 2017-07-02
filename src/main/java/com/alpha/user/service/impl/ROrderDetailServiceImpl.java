package com.alpha.user.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.common.base.BusException;
import com.alpha.common.base.Enums;
import com.alpha.common.dao.ROrderDetailMapper;
import com.alpha.common.model.DsFinanceBean;
import com.alpha.common.model.DsUserBean;
import com.alpha.common.model.FinanceBean;
import com.alpha.common.model.OrderBean;
import com.alpha.common.model.OrderDetailBean;
import com.alpha.common.utils.CollectionUtils;
import com.alpha.common.utils.DateUtil;
import com.alpha.common.utils.StringUtil;
import com.alpha.core.mybatis.BaseMybatisDao;
import com.alpha.user.service.DsFinanceService;
import com.alpha.user.service.DsUserService;
import com.alpha.user.service.RFinanceService;
import com.alpha.user.service.ROrderDetailService;
import com.alpha.user.service.ROrderService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Service
public class ROrderDetailServiceImpl extends BaseMybatisDao<ROrderDetailMapper> implements ROrderDetailService {

	@Autowired
	private ROrderDetailMapper rOrderDetailMapper;

	@Autowired
	private ROrderService rOrderService;

	@Autowired
	private RFinanceService rFinanceService;

	@Autowired
	private DsUserService dsUserService;

	@Autowired
	private DsFinanceService dsFinanceService;

	@Override
	@Transactional
	public boolean buyGoods(String userName, List<Integer> goodsId, List<String> goodsName, List<Integer> goodsNum,
			List<String> goodsPrice) throws BusException {
		// 判断付方余额
		String dbName = StringUtil.getDbName(userName);
		DsUserBean userBean = dsUserService.getUserByName(userName);
		BigDecimal totalAmount = userBean.getAmount();
		BigDecimal goodsAmount = getOrderAmount(goodsNum, goodsPrice);
		BigDecimal leftAmount = totalAmount.subtract(goodsAmount);
		if (leftAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new BusException("账户余额不够!");
		}
		OrderBean order = new OrderBean();
		Date orderTime = new Date();
		String orderId = DateUtil.getOrderId(orderTime);
		order.setOrderId(orderId);
		order.setCreateTime(orderTime);
		order.setUpdateTime(orderTime);
		order.setUserName(userName);
		for (int i = 0; i < goodsId.size(); i++) {
			// 保存订单明细
			Integer goodId = goodsId.get(i);
			String goodName = goodsName.get(i);
			Integer goodNum = goodsNum.get(i);
			BigDecimal goodPrice = new BigDecimal(goodsPrice.get(i));
			OrderDetailBean detail = new OrderDetailBean();
			detail.setGoodsId(goodId);
			detail.setGoodsName(goodName);
			detail.setGoodsNum(goodNum);
			detail.setOrderId(orderId);
			detail.setPerAmount(goodPrice);
			insert(detail);

			// 保存到财务表
			FinanceBean payBean = new FinanceBean();
			payBean.setAmount(goodPrice.multiply(new BigDecimal(goodNum)));
			payBean.setCode(Enums.FinanceCode.C501.getName());
			payBean.setCreateTime(orderTime);
			payBean.setUserName(userName);
			payBean.setToUserName("");
			payBean.setSubject("购物支付-" + goodName);
			rFinanceService.insert(payBean);
		}
		order.setAmount(goodsAmount);
		// 保存订单
		rOrderService.insert(order);

		// 更新ds账户
		dsUserService.updateAmount(userName, leftAmount);
		// 写入ds财务表
		DsFinanceBean record = new DsFinanceBean();
		record.setAddTime(orderTime);
		record.setAmount(goodsAmount);
		record.setCode(Enums.FinanceCode.C501.getName());
		record.setDbName(dbName);
		record.setToUserId(0);
		record.setUserId(userBean.getUserId());
		record.setSubject("");
		record.setCid(0);
		record.setOid(0);
		record.setFlag(1);
		dsFinanceService.insert(record);
		return true;
	}

	public BigDecimal getOrderAmount(List<Integer> goodsNum, List<String> goodsPrice) {
		BigDecimal amount = new BigDecimal(0);
		for (int i = 0; i < goodsNum.size(); i++) {
			BigDecimal goodPrice = new BigDecimal(goodsPrice.get(i));
			amount = amount.add(goodPrice.multiply(new BigDecimal(goodsNum.get(i))));
		}
		return amount;
	}

	@Override
	public List<OrderDetailBean> orderList(String userName) {
		List<OrderBean> orderList = rOrderService.getOrderList(userName);
		if (CollectionUtils.isEmpty(orderList)) {
			return null;
		}
		List<String> orderIds = Lists.newArrayList();
		Map<String, OrderBean> maps = Maps.newHashMap();
		for (OrderBean bean : orderList) {
			orderIds.add(bean.getOrderId());
			maps.put(bean.getOrderId(), bean);
		}
		List<OrderDetailBean> orderDetailList = getListByOrderIds(orderIds);
		if (CollectionUtils.isEmpty(orderDetailList)) {
			return null;
		}
		for (OrderDetailBean bean : orderDetailList) {
			bean.setOrderBean(maps.get(bean.getOrderId()));
		}
		return orderDetailList;
	}

	@Override
	public int insert(OrderDetailBean bean) {
		return rOrderDetailMapper.insert(bean);
	}

	@Override
	public List<OrderDetailBean> getListByOrderIds(List<String> orderIds) {
		return rOrderDetailMapper.getListByOrderIds(orderIds);
	}

}
