package com.quest.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BussinessException.class)
	public ResponseEntity<ErrorVO> handleBussinessValidations(BussinessException exception){
		ErrorVO error = new ErrorVO(exception.getCode(), exception.getMessage());
		return new ResponseEntity<ErrorVO>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorVO> commnonExceptionHandler(Exception e) {
		ErrorVO error = new ErrorVO("417", e.getMessage());
		return new ResponseEntity<ErrorVO>(error, HttpStatus.EXPECTATION_FAILED);
	}
	
}
