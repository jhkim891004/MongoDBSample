package com.sample.mongodb.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {
	private int status;
	private String code;
	private String message;
	private T payload;

	public SuccessResponse(T result, SuccessCode status) {
		this.status = status.getStatus();
		this.code = status.getCode();
		this.message = status.getMessage();
		this.payload = result;
	}
}
