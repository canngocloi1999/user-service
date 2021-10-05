package com.beetsoft.userservice.model.enums;


public enum RoleEnum {
	
	CENTER_OPERATOR("center operator"),
	SUPPILER("suppiler"),
	STORE("store"),
	DELIVERY_DRIVER("delivery driver");

	private String value;

	private RoleEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static RoleEnum fromValue(String text) {
		for (RoleEnum b : RoleEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
