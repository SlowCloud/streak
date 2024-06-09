package com.slowcloud.streak.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	/*
	 * @ExceptionHandler(DataIntegrityViolationException.class) public
	 * ResponseEntity<Void> sqlException() {
	 * 
	 * }
	 */
}
