package com.alpha.common.utils;

public class Enums {

	/**
	 * 企业四要素法人三要素验证
	 */
	public enum CorpVerifyStatus {
		RNAST000("RNAST000", "企业四要素待验证;法人三要素待验证");

		private String name;

		private String value;

		private CorpVerifyStatus(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}

		public static String getValue(String name) {
			for (CorpVerifyStatus item : CorpVerifyStatus.values()) {
				if (item.name == name) {
					return item.value;
				}
			}
			return "";
		}
	}
}