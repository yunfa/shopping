package com.alpha.common.base;

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

	/**
	 * 账号状态
	 */
	public enum UserFlag {
		F0("0", "未激活"), F1("1", "正常"), F2("2", "正常"), F3("3", "正常"), F4("4", "帐号异常禁止登录");

		private String name;

		private String value;

		private UserFlag(String name, String value) {
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
			for (UserFlag item : UserFlag.values()) {
				if (item.name == name) {
					return item.value;
				}
			}
			return "";
		}
	}

	/**
	 * 财务转账状态
	 */
	public enum FinanceCode {
		C501("501", "订单数据"), C502("502", "转账出账"), C503("503", "转账进账");

		private String name;

		private String value;

		private FinanceCode(String name, String value) {
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
			for (FinanceCode item : FinanceCode.values()) {
				if (item.name == name) {
					return item.value;
				}
			}
			return "";
		}
	}

	/**
	 * 数据库名
	 */
	public enum DbName {
		CN("OLITCVUY", "OLITCVUY"), TW("TWN44", "TWN44"), RPAY("RPAY", "RPAY");

		private String name;

		private String value;

		private DbName(String name, String value) {
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
			for (DbName item : DbName.values()) {
				if (item.name == name) {
					return item.value;
				}
			}
			return "";
		}
	}
}