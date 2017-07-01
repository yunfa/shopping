package com.alpha.common.base;

import java.util.Calendar;

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

	/*** 项目根路径 */

	/*** Freemarker 使用的变量 begin **/

	public static final String TARGET = "target";// 标签使用目标

	public static final String OUT_TAG_NAME = "outTagName";// 输出标签Name

	/*** Freemarker 使用的变量 end **/

	/** 其他常用变量 begin **/
	public static final String NAME = "name";

	public static final String ID = "id";

	public static final String TOKEN = "token";

	public static final String LOING_USER = "loing_user";

	/** Long */
	public static final Long ZERO = new Long(0);

	public static final Long ONE = new Long(1);

	public static final Long TWO = new Long(2);

	public static final Long THREE = new Long(3);

	public static final Long EIGHT = new Long(8);

	/** String */
	public static final String S_ZERO = "0";

	public static final String S_ONE = "1";

	public static final String S_TOW = "2";

	public static final String S_THREE = "3";

	/** Integer */
	public static final Integer I_ZERO = 0;

	public static final Integer I_ONE = 1;

	public static final Integer I_TOW = 2;

	public static final Integer I_THREE = 3;

	/** 其他常用变量 end **/

	/** cache常用变量 begin **/
	public static final String CACHE_NAME = "shiro_cache";

	public static final String CACHE_MANAGER = "cacheManager";// cacheManager bean name

	/** cache常用变量 end **/

	/** 当前年份 **/
	public static final int NOW_YEAY = Calendar.getInstance().get(Calendar.YEAR);

	/** 地址 **/
	public static final String DOMAIN_WWW = IConfig.get("domain.www");// 前端域名

	public static final String DOMAIN_CDN = IConfig.get("domain.cdn");// 后台域名

	public static String VERSION = DateUtil.dateToStringWithTime();// 版本号，重启的时间

	// 存储到缓存，标识用户的禁止状态，解决在线用户踢出的问题
	final public static String EXECUTE_CHANGE_USER = "alpha_EXECUTE_CHANGE_USER";
}
