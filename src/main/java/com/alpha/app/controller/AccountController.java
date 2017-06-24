package com.alpha.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alpha.common.controller.BaseController;

/**
 * 账户Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("app")
public class AccountController extends BaseController {

	/**
	 * 管理首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("my_account")
	public String myAccount(HttpServletRequest request) {
		return "redirect:u/login.shtml";
	}
}
