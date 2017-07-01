package com.alpha.common.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PwdUtil {

	private static Logger logger = LoggerFactory.getLogger(PwdUtil.class);

	/**
	 * MD5加密
	 * 
	 * @param pwd 密文
	 * @return
	 */
	private static String getMD5(String pwd) {
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] bs = digest.digest(pwd.getBytes());
			String hexString = "";
			for (byte b : bs) {
				int temp = b & 255;
				if (temp < 16 && temp >= 0) {
					hexString = hexString + "0" + Integer.toHexString(temp);
				} else {
					hexString = hexString + Integer.toHexString(temp);
				}
			}
			return hexString.toUpperCase();
		} catch (Exception e) {
			logger.error("加密出错", e);
		}
		return "";
	}

	/**
	 * MD5直接加密算法
	 * 
	 * @param pwd 密文
	 * @return
	 */
	public static String getOliMD5(String pwd) {
		return getMD5(pwd);
	}

	/**
	 * 打乱之后的MD5加密
	 * 
	 * @param pwd 密文
	 * @return
	 */
	public static String getTWMD5(String pwd) {
		StringBuilder builder = new StringBuilder();
		String str = getMD5(pwd);
		String[] strArray = new String[4];
		for (int i = 0; i < 4; i++) {
			strArray[i] = str.substring(i, i + (i + 1) * 3);
		}
		builder.append(strArray[3]).append(pwd.substring(0, 2)).append(strArray[1]).append(pwd.substring(4))
				.append(strArray[2]).append(pwd.substring(2, 2 + 2)).append(strArray[0]);
		return getMD5(builder.toString()).toUpperCase();
	}

	public static void main(String[] args) {
		// 71B596CB42EE254F7416043D184FC970
		System.out.println(getOliMD5("a11111"));
		// E9E9EE598F1E406F08AC6B55ECC7E748
		System.out.println(getTWMD5("a11111"));
	}
}