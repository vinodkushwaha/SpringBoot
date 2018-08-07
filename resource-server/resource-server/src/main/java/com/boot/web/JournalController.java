package com.boot.web;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.repository.JournalRepository;

@RestController
public class JournalController
{
	@Autowired
	private JournalRepository	repo;

	private String				message	= "Hello world!";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model)
	{
		model.setViewName("index");
		model.addObject("journal", repo.findAll());
		return model;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public void updateMessage(@RequestBody String message)
	{
		this.message = message;
		System.out.println("My Message: " + message);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public Map<String, String> home()
	{
		return Collections.singletonMap("message", message);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Map<String, String> user(Principal user)
	{
		return Collections.singletonMap("message", "user is: " + user.toString());
	}

}
