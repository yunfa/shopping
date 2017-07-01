package com.alpha.common.base;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("rawtypes")
public class HttpResult<T> {

	private static Logger logger = LoggerFactory.getLogger(HttpResult.class);

	private String code = HttpCode.success_200.getName();

	private String msg = HttpCode.success_200.getValue();

	private Date timestamp = new Date();

	@JsonInclude(Include.NON_NULL)
	private T content;

	public HttpResult() {
		super();
	}

	public HttpResult(T content) {
		this.content = content;
	}

	public HttpResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public HttpResult(String code, String msg, T content) {
		this.code = code;
		this.msg = msg;
		this.content = content;
	}

	@SuppressWarnings("unchecked")
	public static HttpResult success() {
		return new HttpResult(null);
	}

	public static <T> HttpResult<T> success(T content) {
		return new HttpResult<T>(content);
	}

	public static HttpResult success(String msg) {
		return new HttpResult(HttpCode.success_200.getName(), msg);
	}

	public static HttpResult success(String code, String msg) {
		return new HttpResult(code, msg);
	}

	public static HttpResult failure(String msg) {
		return new HttpResult(HttpCode.error_500.getName(), msg);
	}

	public static HttpResult failure(String code, String msg) {
		return new HttpResult(code, msg);
	}

	public static <T> HttpResult<T> failure(String code, String msg, T content) {
		return new HttpResult<T>(code, msg, content);
	}

	public static HttpResult failure(Exception e) {
		logger.error("系统错误:", e);
		return failure(HttpCode.error_500.getName(), HttpCode.error_500.getValue(), e.toString());
	}

	public static HttpResult failure(HttpCode errorCode) {
		return failure(errorCode.getName(), errorCode.getValue());
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

	public T getcontent() {
		return (T) content;
	}

	public void setcontent(T content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
