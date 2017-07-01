package com.alpha.common.base;

public class BusException extends Exception {

	private static final long serialVersionUID = 1L;

	private String code = HttpCode.business_600.getName();

	private String msg;

	private HttpCode httpCode = HttpCode.business_600;

	public BusException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public BusException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BusException(HttpCode httpCode) {
		super(httpCode.getValue());
		this.httpCode = httpCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HttpCode getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(HttpCode httpCode) {
		this.httpCode = httpCode;
	}

}
