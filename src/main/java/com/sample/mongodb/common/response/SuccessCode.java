package com.sample.mongodb.common.response;

import lombok.Getter;

@Getter
public enum SuccessCode {

	OK(200, "S001", "OK"),
	SUCCESS_CREATED(201, "S002", "Created."),
	SUCCESS_MODIFIED(201, "S003", "Modified."),
	SUCCESS_DELETED(203, "S004", "Deleted."),

	;

	private final int status;
	private final String code;
	private final String message;

	SuccessCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}


}
