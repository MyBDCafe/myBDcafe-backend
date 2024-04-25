package com.web.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExceptionResponse {
	private int status;
	private LocalDateTime timestamp;
	private String errorClass;
	private String errMsg;
	
}
