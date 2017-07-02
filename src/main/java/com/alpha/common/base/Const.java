package com.alpha.common.base;

import java.util.Calendar;
import java.util.Date;

import com.alpha.common.utils.DateUtil;
import com.alpha.core.config.IConfig;

/**
 * 常量类
 * 
 * @author Li Yunfa
 * @date 2017年6月24日
 */
public class Const {

	// 0:禁止登录
	public static final Long _0 = new Long(0);

	// 1:有效
	public static final Long _1 = new Long(1);

	public static final String CONTEXT_PATH = "contextPath";

	// 标签使用目标
	public static final String TARGET = "target";

	// 输出标签Name
	public static final String OUT_TAG_NAME = "outTagName";

	public static final String NAME = "name";

	public static final String ID = "id";

	public static final String TOKEN = "token";

	public static final String LOING_USER = "loing_user";

	public static final Long ZERO = new Long(0);

	public static final Long ONE = new Long(1);

	public static final Long TWO = new Long(2);

	public static final Long THREE = new Long(3);

	public static final Long EIGHT = new Long(8);

	public static final String S_ZERO = "0";

	public static final String S_ONE = "1";

	public static final String S_TOW = "2";

	public static final String S_THREE = "3";

	public static final Integer I_ZERO = 0;

	public static final Integer I_ONE = 1;

	public static final Integer I_TOW = 2;

	public static final Integer I_THREE = 3;

	public static final String CACHE_NAME = "shiro_cache";

	public static final String CACHE_MANAGER = "cacheManager";

	// 当前年份
	public static final int NOW_YEAY = Calendar.getInstance().get(Calendar.YEAR);

	// 地址前端域名
	public static final String DOMAIN_WWW = IConfig.get("domain.www");

	// 后台域名
	public static final String DOMAIN_CDN = IConfig.get("domain.cdn");
	// 版本号，重启的时间

	public static String VERSION = DateUtil.formatString(new Date(), DateUtil.FMT_DATE_TIMES_PATTERN);

	// 存储到缓存，标识用户的禁止状态，解决在线用户踢出的问题
	final public static String EXECUTE_CHANGE_USER = "alpha_EXECUTE_CHANGE_USER";

	public static String smsCodeKeyPrefix = "sms:";

	public static String userTokenKeyPrefix = "user:";

	// 短信验证码超时时间，秒
	public static int redisExpireTime = 60 * 10;

	// redis最大时间
	public static int redisExpireTimeMax = 0;

	public static int redisDbIndex = 0;
}
