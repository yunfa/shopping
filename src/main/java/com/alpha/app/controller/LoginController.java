package com.alpha.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.common.base.BusException;
import com.alpha.common.base.HttpResult;
import com.alpha.common.controller.BaseController;
import com.alpha.user.dto.UserDto;
import com.alpha.user.service.DsUserService;

/**
 * 登录Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@RestController
@RequestMapping("/app")
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private DsUserService dsUserService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 * @throws BusException
	 */
	@RequestMapping("/send_code")
	public HttpResult<?> sendCode(@RequestParam String userName) throws BusException {
		logger.info("userName={}", userName);
		dsUserService.sendCode(userName);
		return HttpResult.success();
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 * @throws BusException
	 */
	@RequestMapping("/login")
	public HttpResult<?> login(@RequestParam String userName, @RequestParam String userPwd,
			@RequestParam String verifyCode) throws BusException {
		logger.info("userName={},userPwd={},verifyCode={}", userName, userPwd, verifyCode);
		UserDto dto = buildUserDto(dsUserService.login(userName, userPwd, verifyCode));
		return HttpResult.success(dto);
	}

	/**
	 * 修改密码
	 * 
	 * @RequestParam(value = "notIncludeTypeId", required = false)
	 * @param request
	 * @return
	 * @throws BusException
	 */
	@RequestMapping("/update_pwd")
	public HttpResult<?> updatePwd(@RequestParam(required = true) String userName, @RequestParam String userPwd,
			@RequestParam String verifyCode) throws BusException {
		logger.info("userName={},userPwd={},verifyCode={}", userName, userPwd, verifyCode);
		dsUserService.updatePwd(userName, userPwd, verifyCode);
		return HttpResult.success();
	}

}
