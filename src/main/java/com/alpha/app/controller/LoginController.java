package com.alpha.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.common.base.HttpResult;
import com.alpha.common.controller.BaseController;
import com.alpha.common.model.UserBean;
import com.alpha.common.utils.ReflectUtil;

/**
 * 登录Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@RestController
@RequestMapping("/app")
public class LoginController extends BaseController {

	/**
	 * 登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public HttpResult<?> login(HttpServletRequest request) {
		return HttpResult.success(ReflectUtil.setObject(UserBean.class));
	}
}
