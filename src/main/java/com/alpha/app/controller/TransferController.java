package com.alpha.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.common.base.HttpResult;
import com.alpha.common.controller.BaseController;
import com.alpha.common.utils.ReflectUtil;
import com.alpha.transfer.dto.TransterDto;
import com.google.common.collect.Lists;

/**
 * 账户Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@RestController
@RequestMapping("app")
public class TransferController extends BaseController {

	/**
	 * 收付款转账
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("transfer_funds")
	public HttpResult<?> transferFunds(@RequestParam Integer userId, @RequestParam String token,
			@RequestParam Integer payUserId, @RequestParam Integer receiveUserId, @RequestParam String perAmount,
			@RequestParam String transferType) {
		logger.info("userId={},token={},payUserId={},receiveUserId={},perAmount={},transferType={}", userId, token,
				payUserId, receiveUserId, perAmount, transferType);
		return HttpResult.success();
	}

	/**
	 * 收付款转账明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("transfer_record")
	public HttpResult<?> transferRecord(@RequestParam Integer userId, @RequestParam String token) {
		logger.info("userId={},token={}", userId, token);
		List<TransterDto> dtoList = Lists.newArrayList();
		for (int i = 0; i < 17; i++) {
			TransterDto dto = (TransterDto) ReflectUtil.setObject(TransterDto.class);
			dtoList.add(dto);
		}
		return HttpResult.success(dtoList);
	}
}
