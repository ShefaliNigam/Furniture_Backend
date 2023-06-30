package com.application.security.exception;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.application.security.error.ValidationErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadCredentialsException.class)
	public String handleWrongPassword( BadCredentialsException ex) {
		return "Wrong Password";
		
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public String handleWrongCredentials(UsernameNotFoundException ex) {
		return "Login Failed";
	}
	

	@ExceptionHandler(NoSuchElementException.class)
	public String NoDetails(NoSuchElementException ex) {
		return "Incorrect UserName and Password";
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(HttpStatus.BAD_REQUEST,
				"Validation error", null);
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		validationErrorResponse.setErrors(errors);
		return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
	}
}
