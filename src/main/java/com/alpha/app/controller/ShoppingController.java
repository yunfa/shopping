package com.alpha.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alpha.common.controller.BaseController;

/**
 * 购物Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("app")
public class ShoppingController extends BaseController {

	/**
	 * 管理首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("pay_bill")
	public String payBill(HttpServletRequest request) {
		return "redirect:u/login.shtml";
	}
}
