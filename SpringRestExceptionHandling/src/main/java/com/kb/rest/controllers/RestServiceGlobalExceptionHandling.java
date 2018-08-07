package com.kb.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kb.rest.exception.UserNotFoundException;
import com.kb.rest.model.User;

@Controller
@RequestMapping("/user/globalExceptionHandler")
class RestServiceGlobalExceptionHandling {

	@RequestMapping(value = "/getSpecificUser/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUserForId(@PathVariable("id") int id) throws Exception {
		User user = new User();
		user.setId(id);
		user.setName("John");
		user.setAge(45);
		if (id != 1) {
			throw new UserNotFoundException("User not found");
		}

		// Intentionally throwing exception
		int ageByZero = user.getAge() / 0;

		user.setAge(ageByZero);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

}
