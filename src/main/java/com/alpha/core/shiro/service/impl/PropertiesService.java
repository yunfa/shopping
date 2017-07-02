package com.alpha.core.shiro.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 配置文件相关
 * 
 * @author Li Yunfa
 * @date 2017年7月1日
 */
@Service
public class PropertiesService {

	@Value("${smsurl}")
	private String smsurl = "http://api2.santo.cc/submit";

	@Value("${smscommand}")
	private String smscommand = "MT_REQUEST";

	@Value("${smscpid}")
	private String smscpid = "test1";

	@Value("${smscppwd}")
	private String smscppwd = "santo201704";

	@Value("${env}")
	private String env;

	public String getSmsurl() {
		return smsurl;
	}

	public void setSmsurl(String smsurl) {
		this.smsurl = smsurl;
	}

	public String getSmscommand() {
		return smscommand;
	}

	public void setSmscommand(String smscommand) {
		this.smscommand = smscommand;
	}

	public String getSmscpid() {
		return smscpid;
	}

	public void setSmscpid(String smscpid) {
		this.smscpid = smscpid;
	}

	public String getSmscppwd() {
		return smscppwd;
	}

	public void setSmscppwd(String smscppwd) {
		this.smscppwd = smscppwd;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

}
