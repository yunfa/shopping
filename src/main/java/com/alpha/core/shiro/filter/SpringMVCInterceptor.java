package com.alpha.core.shiro.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 拦截URL验证user token
 * 
 * @author Li Yunfa
 * @date 2017年7月2日
 */
public class SpringMVCInterceptor extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(SpringMVCInterceptor.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain arg2)
			throws ServletException, IOException {
		try {
			new ResponseUtil().verifyUrl(request, response);
		} catch (Exception e) {
			logger.error("拦截错误", e);
		}
	}
}