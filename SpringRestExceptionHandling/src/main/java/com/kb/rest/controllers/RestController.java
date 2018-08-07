package com.kb.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kb.rest.model.User;
import com.kb.rest.test.Coffee;
import com.kb.rest.test.Company;
//fully tested 
@Controller
@RequestMapping("/user")
public class RestController {

	@RequestMapping(value = "/getSpecificUser/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUserForId(@PathVariable ("id") int id) {
		User user = new User();
		user.setId(id);
		user.setName("John");
		user.setAge(45);
		if(id !=1){
			throw new RuntimeException();
		}
		
		return user;
	}
	
	@RequestMapping(value = "/mobile/getcomp", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Company>> listforCompanies() {      
	    List<Company> listOfCompanies= new ArrayList<Company>();     
	    Company cmp = new Company();
	    cmp.setCid("100");
	    cmp.setCname("TCS");
	    Company cmp1 = new Company();
	    cmp1.setCid("200");
	    cmp1.setCname("Wipro");
	    listOfCompanies.add(cmp);
	    listOfCompanies.add(cmp1);
	    return new ResponseEntity<List<Company>>(listOfCompanies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/coffee/{name}", method = RequestMethod.GET)
	public @ResponseBody Coffee getCoffeeInXML(@PathVariable("name") String name) {
		Coffee coffee = new Coffee(name, 100);
		return coffee;

	}

}
