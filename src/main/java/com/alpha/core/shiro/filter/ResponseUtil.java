package com.alpha.core.shiro.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alpha.common.base.Const;
import com.alpha.common.base.HttpCode;
import com.alpha.common.model.DsUserBean;
import com.alpha.common.utils.SpringContextUtil;
import com.alpha.common.utils.StringUtil;
import com.alpha.core.shiro.cache.JedisManager;
import com.google.common.collect.Sets;

public class ResponseUtil {

	private Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

	public boolean verifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug(request.getParameter("userName"));
		String url = request.getRequestURI();
		if (url.startsWith("/app/") && (!containIgnoreUrl(url))) {
			// app下的其它url都需要userName和token字段
			logger.debug("app请求做token校验...");
			String token = request.getParameter("token");
			String userNameUrl = request.getParameter("userName");
			if (StringUtil.isBlank(token) || StringUtil.isBlank(userNameUrl)) {
				outError(response, HttpCode.error_300);
				return false;
			}
			JedisManager jedisManager = SpringContextUtil.getBean("jedisManager", JedisManager.class);
			String key = Const.userTokenKeyPrefix + token;
			byte[] userInfoByte = jedisManager.getValueByKey(Const.redisDbIndex, key.getBytes());
			if (userInfoByte == null) {
				outError(response, HttpCode.error_300);
				return false;
			}
			String tokenCache = new String(userInfoByte);
			DsUserBean userBean = JSONObject.toJavaObject(JSONObject.parseObject(tokenCache), DsUserBean.class);
			String userNameCache = userBean.getUserName();
			if (!userNameCache.equalsIgnoreCase(userNameUrl)) {
				outError(response, HttpCode.error_300);
				return false;
			}
		}
		return true;
	}

	private boolean containIgnoreUrl(String url) {
		Set<String> ignorePrefixSet = Sets.newHashSet("/app/send_code.shtml", "/app/login.shtml",
				"/app/update_pwd.shtml");
		for (String strPrefix : ignorePrefixSet) {
			if (url.startsWith(strPrefix)) {
				return true;
			}
		}
		return false;
	}

	private void outError(HttpServletResponse response, HttpCode httpCode) throws IOException {
		ModelMap modelMap = new ModelMap();
		modelMap.put("code", httpCode.getName());
		modelMap.put("desc", httpCode.getValue());
		modelMap.put("timestamp", System.currentTimeMillis());
		response.setContentType("application/json;charset=UTF-8");
		logger.info("error json:{}", JSON.toJSON(modelMap));
		byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
		response.getOutputStream().write(bytes);
	}
}
