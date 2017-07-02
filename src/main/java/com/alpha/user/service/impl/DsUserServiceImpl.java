package com.alpha.user.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.common.base.BusException;
import com.alpha.common.base.Const;
import com.alpha.common.base.Enums;
import com.alpha.common.dao.DsUserMapper;
import com.alpha.common.model.DsUserBean;
import com.alpha.common.utils.MathUtil;
import com.alpha.common.utils.PwdUtil;
import com.alpha.common.utils.StringUtil;
import com.alpha.core.mybatis.BaseMybatisDao;
import com.alpha.core.shiro.cache.JedisManager;
import com.alpha.core.shiro.service.impl.PropertiesService;
import com.alpha.user.service.DsUserService;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Service
public class DsUserServiceImpl extends BaseMybatisDao<DsUserMapper> implements DsUserService {

	private static Logger logger = LoggerFactory.getLogger(DsUserServiceImpl.class);

	@Autowired
	private DsUserMapper dsUserMapper;

	@Autowired
	private JedisManager jedisManage;

	@Autowired
	private PropertiesService propertiesService;

	@Override
	public boolean sendCode(String userName) throws BusException {
		String dbName = StringUtil.getDbName(userName);
		DsUserBean user = dsUserMapper.getUserByName(userName, dbName);
		logger.info("user:{}", user);
		if (user == null) {
			throw new BusException("用户:" + userName + "不存在!");
		}
		Integer userId = user.getUserId();
		String mobile = dsUserMapper.getMobileByUserId(userId, dbName);
		logger.info("mobile:{}", mobile);
		if (StringUtil.isBlank(mobile)) {
			throw new BusException("您还没有设置手机号码!");
		}
		return sendVerifyCode(mobile);
	}

	@Override
	public DsUserBean login(String userName, String userPwd, String verifyCode) throws BusException {
		String dbName = StringUtil.getDbName(userName);
		DsUserBean user = dsUserMapper.getUserByName(userName, dbName);
		logger.info("user:{}", user);
		if (user == null) {
			throw new BusException("用户:" + userName + "不存在!");
		}
		String userFlag = user.getUserFlag();
		if (Enums.UserFlag.F0.getName().equals(userFlag)) {
			// 0:用户未激活 提示用户 到我们登录官网处理!
			throw new BusException("用户状态未激活,请登录官网处理!");
		}
		if (Enums.UserFlag.F4.getName().equals(userFlag)) {
			// 4:您的帐号出现异常，已被禁止登录！
			throw new BusException("您的帐号出现异常,已被禁止登录!");
		}
		Integer userId = user.getUserId();
		String mobile = dsUserMapper.getMobileByUserId(userId, dbName);
		logger.info("mobile:{}", mobile);
		String verifyCodeCache = getVerifyCode(mobile);
		logger.info("verifyCodeCache:{}", verifyCodeCache);
		if (StringUtil.isBlank(verifyCodeCache)) {
			throw new BusException("请重新发送验证码!");
		}
		if (!verifyCodeCache.equalsIgnoreCase(verifyCode)) {
			throw new BusException("手机验证码错误!");
		}
		String pwdMd5 = PwdUtil.getOliMD5(userPwd);
		if (Enums.DbName.TW.getName().equalsIgnoreCase(dbName)) {
			pwdMd5 = PwdUtil.getTWMD5(userPwd);
		}
		if (!pwdMd5.equals(user.getUserPwd1())) {
			throw new BusException("用户密码不正确!");
		}
		// 登录成功
		String token = StringUtil.getUUID();
		String key = Const.userTokenKeyPrefix + token;
		String value = user.toString();
		try {
			jedisManage.saveValueByKey(Const.redisDbIndex, key.getBytes(), value.getBytes(), Const.redisExpireTimeMax);
		} catch (Exception e) {
			logger.error("保存用户token到redis出错", e);
		}
		user.setToken(token);
		return user;
	}

	@Override
	public boolean updatePwd(String userName, String userPwd, String verifyCode) throws BusException {
		String dbName = StringUtil.getDbName(userName);
		DsUserBean user = dsUserMapper.getUserByName(userName, dbName);
		logger.info("user:{}", user);
		if (user == null) {
			throw new BusException("用户:" + userName + "不存在!");
		}
		Integer userId = user.getUserId();
		String mobile = dsUserMapper.getMobileByUserId(userId, dbName);
		logger.info("mobile:{}", mobile);
		String verifyCodeCache = getVerifyCode(mobile);
		logger.info("verifyCodeCache:{}", verifyCodeCache);
		if (StringUtil.isBlank(verifyCodeCache)) {
			throw new BusException("请重新发送验证码!");
		}
		if (!verifyCodeCache.equalsIgnoreCase(verifyCode)) {
			throw new BusException("手机验证码错误!");
		}
		String userPwd1 = PwdUtil.getOliMD5(userPwd);
		if (Enums.DbName.TW.getName().equalsIgnoreCase(dbName)) {
			userPwd1 = PwdUtil.getTWMD5(userPwd);
		}
		user.setUserPwd1(userPwd1);
		user.setDbName(dbName);
		dsUserMapper.updateLoginPwd(user);
		return true;
	}

	private boolean sendVerifyCode(String mobile) {
		String code = MathUtil.getRandom(4);
		if ("dev".equals(propertiesService.getEnv())) {
			code = "0000";
		}
		logger.info("verify code:{}", code);
		String key = Const.smsCodeKeyPrefix + mobile;
		try {
			jedisManage.saveValueByKey(Const.redisDbIndex, key.getBytes(), code.getBytes(), Const.redisExpireTime);
		} catch (Exception e) {
			logger.error("保存redis短信验证码出错", e);
		}
		// TODO 调用短信接口发送验证码
		return true;
	}

	private String getVerifyCode(String mobile) {
		String key = Const.smsCodeKeyPrefix + mobile;
		String code = "";
		try {
			byte[] valueByKey = jedisManage.getValueByKey(Const.redisDbIndex, key.getBytes());
			code = valueByKey == null ? "" : new String(valueByKey);
		} catch (Exception e) {
			logger.error("获取redis短信验证码出错", e);
		}
		return code;
	}

	@Override
	public DsUserBean getUserByName(String userName) {
		String dbName = StringUtil.getDbName(userName);
		return dsUserMapper.getUserByName(userName, dbName);
	}

	@Override
	public boolean updateAmount(String userName, BigDecimal amount) {
		DsUserBean bean = new DsUserBean();
		String dbName = StringUtil.getDbName(userName);
		bean.setAmount(amount);
		bean.setUserName(userName);
		bean.setDbName(dbName);
		return dsUserMapper.updateAmount(bean) > 0;
	}

}
