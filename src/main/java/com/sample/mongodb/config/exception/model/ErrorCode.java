package com.sample.mongodb.config.exception.model;

import lombok.Getter;

@Getter
public enum ErrorCode {
	/**
	 * CODE
	 * 400: 문법상 오류
	 * 401: 인증 오류(로그인 실패, ...)
	 * 403: 인가 오류(권한없는 사용자의 자원 Access 요청)
	 * 404: Not Found 오류
	 * 405: 요청 메서드 오류
	 * 408: timeout
	 * 500: 서버 오류
	 */
	// Common
	INVALID_INPUT_VALUE(400, "E001", "Invalid Input Value"),
	INVALID_TYPE_VALUE(400, "E002", "Invalid Type Value"),
	METHOD_NOT_ALLOWED(405, "E003", "Invalid Method"),
	ENTITY_NOT_FOUND(400, "E004", "Entity Not Found"),
	INTERNAL_SERVER_ERROR(500, "E005", "Server Error"),
	MISSING_REQUEST_PARAM(400, "E006", "Missing Request Param"),
	INVALID_VALID_ANNOTATION(400, "E007", "Invalid Valid Annotation"),

	// Access
	ACCESS_DENIED(401, "A001", "Access is Denied"),
	EXPIRED_TOKEN(401, "A002", "Token is Expired"),
	NOT_FOUND_TOKEN(401, "A003", "Token Not Found"),
	INVALID_SIGNATURE(401, "A004", "Token Do Not Match"),
	FORBIDDEN(403, "A005", "Forbidden"),


	// Validation
	USERNAME_NOT_FOUND(400, "M001", "Member Not Found"),
	USERNAME_IS_EXISTS(400, "M002", "MemberId is Exists"),
	PASSWORD_NOT_MATCH(400, "M003", "Passwords Do Not Match"),


	;

	private final int status;
	private final String code;
	private final String message;

	ErrorCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
