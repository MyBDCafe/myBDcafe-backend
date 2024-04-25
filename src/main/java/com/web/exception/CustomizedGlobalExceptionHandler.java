package com.web.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomizedGlobalExceptionHandler{
	
	@Autowired
	MessageSource ms;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ExceptionResponse validationExceptionHandler(MethodArgumentNotValidException e) {		
		
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.status(e.getStatusCode().value())
				.timestamp(LocalDateTime.now())
				.errorClass(e.getClass().getName())
				.errMsg(ms.getMessage(e.getBindingResult().getFieldErrors().get(0), null))
				.build();
		
		return exceptionResponse;
    }
	
	@ExceptionHandler
	ExceptionResponse allExceptionHandler(Exception e) {
		
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(LocalDateTime.now())
				.errorClass(e.getClass().getName())
				.errMsg(e.getMessage())
				.build();
		
		return exceptionResponse;
	}
	
}

