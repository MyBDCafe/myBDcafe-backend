package com.web.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class CustomizedGlobalExceptionHandler{
	
	@Autowired
	MessageSource ms;
	
	//NotValid 검증 예외처리
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
	
	//예외처리 response
	public ExceptionResponse getExceptionResponse(int status, Exception e) {
		
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.status(status)
				.timestamp(LocalDateTime.now())
				.errorClass(e.getClass().getName())
				.errMsg(e.getMessage())
				.build();
		
		return exceptionResponse;
	}
	
	//NullPoint 예외
	@ExceptionHandler(NullPointerException.class)
	ExceptionResponse nullPointerExceptionHandler(NullPointerException e) {
		return getExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
	}
	
	//NoResourceFoundException 예외
	@ExceptionHandler(NoResourceFoundException.class)
	ExceptionResponse noResourceFoundExceptionHandler(NoResourceFoundException e) {
		return getExceptionResponse(HttpStatus.BAD_REQUEST.value(), e);
	}
	
	
	//기타 예외
	@ExceptionHandler
	ExceptionResponse otherExceptionHandler(Exception e) {
		
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(LocalDateTime.now())
				.errorClass(e.getClass().getName())
				.errMsg(e.getMessage())
				.build();
		
		return exceptionResponse;
	}
	
}

