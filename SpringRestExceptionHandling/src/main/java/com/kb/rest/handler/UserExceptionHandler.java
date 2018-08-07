package com.kb.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.kb.rest.error.UserErrorResponse;
import com.kb.rest.exception.UserNotFoundException;

@ControllerAdvice	
public class UserExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserErrorResponse> handleUserNotFoundException(Exception ex) {
		UserErrorResponse errorResponse = new UserErrorResponse();
		errorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		errorResponse.setErrorMessage(ex.getMessage());
		return new ResponseEntity<UserErrorResponse>(errorResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<UserErrorResponse> handleGenericException(Exception ex) {
		System.out.println("Runtime exception");
		UserErrorResponse errorResponse = new UserErrorResponse();
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage("There is some techncal issue");
		return new ResponseEntity<UserErrorResponse>(errorResponse, HttpStatus.OK);
	}

}
