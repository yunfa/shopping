package com.alpha.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.common.base.HttpResult;
import com.alpha.common.controller.BaseController;
import com.alpha.common.utils.ReflectUtil;
import com.alpha.order.dto.OrderDto;
import com.google.common.collect.Lists;

/**
 * 购物Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@RestController
@RequestMapping("app")
public class OrderController extends BaseController {

	/**
	 * 商品购买
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("buy_goods")
	public HttpResult<?> buyGoods(@RequestParam Integer userId, @RequestParam String token,
			@RequestParam List<Integer> goodsId, @RequestParam List<String> goodsName,
			@RequestParam List<Integer> goodsNum, @RequestParam List<String> goodsPrice) {
		logger.info("userId={},token={},goodsId={},goodsName={},goodsNum={},goodsPrice={}", userId, token, goodsId,
				goodsName, goodsNum, goodsPrice);
		return HttpResult.success();
	}

	/**
	 * 我的订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("my_orders")
	public HttpResult<?> myOrders(@RequestParam Integer userId, @RequestParam String token) {
		logger.info("userId={},token={}", userId, token);
		List<OrderDto> orderDtoList = Lists.newArrayList();
		for (int i = 0; i < 13; i++) {
			OrderDto dto = (OrderDto) ReflectUtil.setObject(OrderDto.class);
			dto.setOrderTime(new Date().getTime());
			orderDtoList.add(dto);
		}
		return HttpResult.success(orderDtoList);
	}
}
