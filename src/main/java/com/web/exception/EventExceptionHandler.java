package com.web.exception;

import java.text.ParseException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice("com.web.controller")
public class EventExceptionHandler {

	@ExceptionHandler
	public ErrorResult exceptionHandler(Exception e){
		return new ErrorResult("오류 발생", e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ErrorResult exHandle(Exception e) {
		System.out.println("null 오류");
		return new ErrorResult("NullPointerException 발생", e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ParseException.class)
    public ErrorResult handleParseException(ParseException e) {
        return new ErrorResult("파싱 중 오류 발생", e.getMessage());
    }
	
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NumberFormatException.class)
    public ErrorResult handleNumberFormatException(NumberFormatException e) {
        return new ErrorResult("NumberFormatException 발생", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResult handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ErrorResult("DB 중복저장 오류", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TransactionSystemException.class)
    public ErrorResult handleTransactionSystemException(TransactionSystemException e) {
        return new ErrorResult("트랜잭션 중 오류 발생", e.getMessage());
    }	
}
