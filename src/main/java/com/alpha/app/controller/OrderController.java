package com.alpha.app.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.common.base.BusException;
import com.alpha.common.base.HttpResult;
import com.alpha.common.controller.BaseController;
import com.alpha.order.dto.OrderDetailDto;
import com.alpha.user.service.ROrderDetailService;

/**
 * 购物Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@RestController
@RequestMapping("/app")
public class OrderController extends BaseController {

	@Autowired
	private ROrderDetailService rOrderDetailService;

	/**
	 * 商品购买
	 * 
	 * @param request
	 * @return
	 * @throws BusException
	 */
	@RequestMapping(value = "/buy_goods", method = RequestMethod.POST)
	public HttpResult<?> buyGoods(@RequestParam String userName, @RequestParam String token,
			@RequestParam List<Integer> goodsId, @RequestParam List<String> goodsName,
			@RequestParam List<Integer> goodsNum, @RequestParam List<String> goodsPrice) throws BusException {
		logger.info("userName={},token={},goodsId={},goodsName={},goodsNum={},goodsPrice={}", userName, token, goodsId,
				goodsName, goodsNum, goodsPrice);
		int idLen = goodsId.size();
		int nameLen = goodsName.size();
		int numLen = goodsNum.size();
		int priceLen = goodsPrice.size();
		if (!(idLen == nameLen && nameLen == numLen && numLen == priceLen)) {
			throw new BusException("入参长度有问题!");
		}
		for (int i = 0; i < goodsId.size(); i++) {
			Integer goodId = goodsId.get(i);
			Integer goodNum = goodsNum.get(i);
			BigDecimal goodPrice = new BigDecimal(goodsPrice.get(i));
			if (goodId < 1) {
				throw new BusException("商品ID不能小于1!");
			}
			if (goodNum < 1) {
				throw new BusException("商品数量不能小于1!");
			}
			if (goodPrice.compareTo(BigDecimal.ZERO) < 0) {
				throw new BusException("商品金额不能小于0!");
			}
		}
		rOrderDetailService.buyGoods(userName, goodsId, goodsName, goodsNum, goodsPrice);
		return HttpResult.success();
	}

	/**
	 * 我的订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my_orders", method = RequestMethod.GET)
	public HttpResult<?> myOrders(@RequestParam String userName, @RequestParam String token) {
		logger.info("userName={},token={}", userName, token);
		List<OrderDetailDto> orderDtoList = buildOrderDetailDto(rOrderDetailService.orderList(userName));
		return HttpResult.success(orderDtoList);
	}
}
