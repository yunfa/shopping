package com.alpha.common.utils;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * spoon密码加密算法
 * 
 * @author Li Yunfa
 * @date 2017年6月20日
 */
public class EncryUtil {

	private static Logger logger = LoggerFactory.getLogger(EncryUtil.class);

	private static final int RADIX = 16;

	private static final String SEED = "0933910847463829827159347601486730416058";

	public static final String encryptPassword(String password) {
		if (password == null)
			return "";
		if (password.length() == 0)
			return "";
		BigInteger bi_passwd = new BigInteger(password.getBytes());
		BigInteger bi_r0 = new BigInteger(SEED);
		BigInteger bi_r1 = bi_r0.xor(bi_passwd);
		return bi_r1.toString(RADIX);
	}

	public static final String decryptPassword(String encrypted) {
		if (encrypted == null)
			return "";
		if (encrypted.length() == 0)
			return "";
		BigInteger bi_confuse = new BigInteger(SEED);
		try {
			BigInteger bi_r1 = new BigInteger(encrypted, RADIX);
			BigInteger bi_r0 = bi_r1.xor(bi_confuse);
			return new String(bi_r0.toByteArray());
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) {
		logger.debug("Encrypted " + encryptPassword("Lls16888appuser"));
		logger.debug(decryptPassword("2bed4c3bb5b91cadcf318be60cb81aac8"));
	}
}
