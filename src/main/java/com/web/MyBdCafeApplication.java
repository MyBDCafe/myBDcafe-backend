package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@EnableJpaAuditing
@SpringBootApplication
public class MyBdCafeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MyBdCafeApplication.class, args);
	}

}

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // 예외를 로깅하거나 다른 처리를 수행할 수 있습니다.
        ex.printStackTrace();
        
        // 클라이언트에게 적절한 응답을 보내줍니다.
        return ResponseEntity.badRequest().body("Invalid request. Please check your request.");
    }
}