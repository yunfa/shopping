package com.alpha.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.common.base.BusException;
import com.alpha.common.base.HttpResult;
import com.alpha.common.controller.BaseController;
import com.alpha.common.model.FinanceBean;
import com.alpha.common.utils.StringUtil;
import com.alpha.user.service.RFinanceService;

/**
 * 账户Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@RestController
@RequestMapping("/app")
public class TransferController extends BaseController {

	@Autowired
	private RFinanceService rFinanceService;

	/**
	 * 收付款转账
	 * 
	 * @param request
	 * @return
	 * @throws BusException
	 */
	@RequestMapping(value = "/transfer_funds", method = RequestMethod.POST)
	public HttpResult<?> transferFunds(@RequestParam String userName, @RequestParam String token,
			@RequestParam String payUserName, @RequestParam String receiveUserName, @RequestParam String perAmount,
			@RequestParam String transferType) throws BusException {
		logger.info("userName={},token={},payUserName={},receiveUserName={},perAmount={},transferType={}", userName,
				token, payUserName, receiveUserName, perAmount, transferType);
		if (!StringUtil.equalsDb(payUserName, receiveUserName)) {
			throw new BusException("不同地区暂不支持转账!");
		}
		rFinanceService.transferFunds(payUserName, receiveUserName, perAmount, transferType);
		return HttpResult.success();
	}

	/**
	 * 收付款转账明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/transfer_record", method = RequestMethod.GET)
	public HttpResult<?> transferRecord(@RequestParam String userName, @RequestParam String token) {
		logger.info("userName={},token={}", userName, token);
		List<FinanceBean> dtoList = rFinanceService.transferRecord(userName);
		return HttpResult.success(buildFinanceDto(dtoList));
	}
}
