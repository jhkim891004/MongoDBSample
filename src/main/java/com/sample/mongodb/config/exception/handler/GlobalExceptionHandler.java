package com.sample.mongodb.config.exception.handler;

import com.sample.mongodb.common.response.ErrorResponse;
import com.sample.mongodb.config.exception.BusinessException;
import com.sample.mongodb.config.exception.model.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.UnexpectedTypeException;
import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 *  Valid or Validated Annotation 으로 binding error 발생시 발생
	 *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
	 *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("handleMethodArgumentNotValidException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.INVALID_INPUT_VALUE.getStatus()));
	}

	/**
	 * ModelAttribute Annotation 으로 binding error 발생시 BindException 발생한다.
	 * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
	 */
	@ExceptionHandler(BindException.class)
	private ResponseEntity<ErrorResponse> handleBindException(BindException e) {
		log.error("handleBindException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.INVALID_INPUT_VALUE.getStatus()));
	}

	/**
	 * 조회된 데이터가 없을 시 발생
	 */
	@ExceptionHandler(NoSuchElementException.class)
	private ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
		log.error("handleNoSuchElementException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.ENTITY_NOT_FOUND);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.ENTITY_NOT_FOUND.getStatus()));
	}

	/**
	 * enum type 일치하지 않아 binding 못할 경우 발생
	 * 주로 @RequestParam enum 으로 binding 못했을 경우 발생
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		log.error("handleMethodArgumentTypeMismatchException", e);
		final ErrorResponse response = ErrorResponse.of(e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Parameter 가 정상적으로 넘어 오지 않았을 때 발생
	 * 주로 @RequestParam binding 못했을 경우 발생
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	private ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		log.error("MissingServletRequestParameterException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.MISSING_REQUEST_PARAM);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.MISSING_REQUEST_PARAM.getStatus()));
	}

	/**
	 * JSON parsing 실패 시 발생
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	private ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.error("MissingServletRequestParameterException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.MISSING_REQUEST_PARAM);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.MISSING_REQUEST_PARAM.getStatus()));
	}

	/**
	 * 지원하지 않은 HTTP method 호출 할 경우 발생
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	private ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("handleHttpRequestMethodNotSupportedException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.METHOD_NOT_ALLOWED.getStatus()));
	}

	/**
	 * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생
	 */
	@ExceptionHandler(AccessDeniedException.class)
	private ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
		log.error("handleAccessDeniedException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.ACCESS_DENIED);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.ACCESS_DENIED.getStatus()));
	}

	/**
	 * 잘못된 validation 어노테이션 설정
	 */
	@ExceptionHandler(UnexpectedTypeException.class)
	private ResponseEntity<ErrorResponse> handleUnexpectedTypeException(UnexpectedTypeException e) {
		log.error("handleUnexpectedTypeException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_VALID_ANNOTATION);
		return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.INVALID_VALID_ANNOTATION.getStatus()));
	}

	/**
	 * 공통 예외
	 */
	@ExceptionHandler(BusinessException.class)
	private ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
		log.error("handleBusinessException", e);
		final ErrorCode errorCode = e.getErrorCode();
		final ErrorResponse response = ErrorResponse.of(errorCode);
		return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
	}


	@ExceptionHandler(Exception.class)
	private ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("handleException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
