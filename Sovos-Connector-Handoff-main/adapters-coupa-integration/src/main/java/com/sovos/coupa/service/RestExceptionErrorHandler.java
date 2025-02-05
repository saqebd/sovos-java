package com.sovos.coupa.service;

import com.sovos.coupa.controller.dto.ApiErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalStateException.class)
	protected ResponseEntity<ApiErrorResponse> handleIllegalArguments(
			IllegalStateException e) {

		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		apiErrorResponse.setMessage(e.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiErrorResponse> handleGeneralError(
			Exception e) {

		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		apiErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiErrorResponse.setMessage(e.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}