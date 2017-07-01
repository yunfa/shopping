package com.alpha.user.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alpha.common.base.Const;
import com.alpha.common.controller.BaseController;
import com.alpha.common.model.UserBean;
import com.alpha.common.utils.StringUtil;
import com.alpha.common.utils.VerifyCodeUtils;
import com.alpha.core.shiro.token.TokenManager;
import com.alpha.user.service.UserService;

/**
 * 用户登录Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("u")
public class UserLoginController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserLoginController.class);

	@Resource
	private UserService userService;

	/**
	 * 登录跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("user/login");
	}

	/**
	 * 注册跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("user/register");
	}

	/**
	 * 注册 && 登录
	 * 
	 * @param vcode 验证码
	 * @param entity UUser实体
	 * @return
	 */
	@RequestMapping(value = "subRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> subRegister(String vcode, UserBean entity) {
		resultMap.put("status", 400);
		if (!VerifyCodeUtils.verifyCode(vcode)) {
			resultMap.put("message", "验证码不正确！");
			return resultMap;
		}
		String email = entity.getEmail();

		UserBean user = userService.findUserByEmail(email);
		if (null != user) {
			resultMap.put("message", "帐号|Email已经存在！");
			return resultMap;
		}
		Date date = new Date();
		entity.setCreateTime(date);
		entity.setLastLoginTime(date);
		// 把密码md5
		entity = userService.md5Pswd(entity);
		// 设置有效
		entity.setStatus(Const._1);

		entity = userService.insert(entity);
		logger.debug("注册插入完毕！{}", JSONObject.toJSONString(entity).toString());
		entity = TokenManager.login(entity, Boolean.TRUE);
		logger.debug("注册后，登录完毕！{}", JSONObject.toJSONString(entity).toString());
		resultMap.put("message", "注册成功！");
		resultMap.put("status", 200);
		return resultMap;
	}

	/**
	 * 登录提交
	 * 
	 * @param entity 登录的UserBean
	 * @param rememberMe 是否记住
	 * @param request request，用来取登录之前Url地址，用来登录后跳转到没有登录之前的页面。
	 * @return
	 */
	@RequestMapping(value = "submitLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitLogin(UserBean entity, Boolean rememberMe, HttpServletRequest request) {
		try {
			entity = TokenManager.login(entity, rememberMe);
			resultMap.put("status", 200);
			resultMap.put("message", "登录成功");

			// shiro 获取登录之前的地址 之前0.1版本这个没判断空。
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			String url = null;
			if (null != savedRequest) {
				url = savedRequest.getRequestUrl();
			}
			logger.debug("获取登录之前的URL:{}", url);
			// 如果登录之前没有地址，那么就跳转到首页。
			if (StringUtil.isBlank(url) || "/".equals(url)) {
				url = request.getContextPath() + "/user/index.shtml";
			}
			// 跳转地址
			resultMap.put("back_url", url);
		} catch (DisabledAccountException e) {
			resultMap.put("status", 500);
			resultMap.put("message", "帐号已经禁用。");
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "帐号或密码错误");
		}

		return resultMap;
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> logout() {
		try {
			TokenManager.logout();
			resultMap.put("status", 200);
		} catch (Exception e) {
			resultMap.put("status", 500);
			logger.error("errorMessage", e);
		}
		return resultMap;
	}
}
