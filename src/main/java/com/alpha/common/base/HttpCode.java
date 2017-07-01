package com.alpha.common.base;

/**
 * Http返回结果
 * 
 * @author Li Yunfa
 * @date 2017年6月24日
 */
public enum HttpCode {
	success_200("200", "成功!"), //
	error_500("500", "系统内部错误!"), //
	business_600("600", "业务错误!");//

	private String name;

	private String value;

	private HttpCode(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public static String getValue(Integer name) {
		for (HttpCode item : HttpCode.values()) {
			if (item.name.equals(name)) {
				return item.value;
			}
		}
		return "";
	}
}