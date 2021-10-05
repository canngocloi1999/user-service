package com.beetsoft.userservice.model.enums;

public enum StatusEnum {
	
	ENABLE("1"),
	DISABLE("0");
	
	private String value;
	
	private StatusEnum(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static StatusEnum fromValue(String text) {
		for (StatusEnum b : StatusEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
