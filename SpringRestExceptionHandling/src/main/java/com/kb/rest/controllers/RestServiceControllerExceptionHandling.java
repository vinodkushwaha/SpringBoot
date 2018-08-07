package com.kb.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kb.rest.error.UserErrorResponse;
import com.kb.rest.exception.UserNotFoundException;
import com.kb.rest.model.User;

@Controller
@RequestMapping(value= "/user/controllerExceptionHandler")
public class RestServiceControllerExceptionHandling {

	@RequestMapping(value = "/getSpecificUser/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUserForId(@PathVariable("id") int id) throws UserNotFoundException {
		User user = new User();
		user.setId(id);
		user.setName("John");
		user.setAge(45);
		if (id != 1) {
			throw new UserNotFoundException("User not found");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserErrorResponse> handleUserNotFoundException(Exception ex) {
		UserErrorResponse errorResponse = new UserErrorResponse();
		errorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		errorResponse.setErrorMessage(ex.getMessage());
		return new ResponseEntity<UserErrorResponse>(errorResponse, HttpStatus.OK);
	}

}
