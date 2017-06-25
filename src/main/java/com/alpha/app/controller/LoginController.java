package com.alpha.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.common.base.HttpResult;
import com.alpha.common.controller.BaseController;
import com.alpha.common.utils.ReflectUtil;
import com.alpha.user.dto.UserDto;

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

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public HttpResult<?> login(@RequestParam String userName, @RequestParam String userPwd,
			@RequestParam String verifyCode) {
		logger.info("userName={},userPwd={},verifyCode={}", userName, userPwd, verifyCode);
		return HttpResult.success(ReflectUtil.setObject(UserDto.class));
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/update_pwd")
	public HttpResult<?> updatePwd(@RequestParam String userName, @RequestParam String userPwd,
			@RequestParam String verifyCode) {
		logger.info("userName={},userPwd={},verifyCode={}", userName, userPwd, verifyCode);
		return HttpResult.success();
	}

}
