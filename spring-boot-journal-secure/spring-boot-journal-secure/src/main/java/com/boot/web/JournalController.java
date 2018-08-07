package com.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.repository.JournalRepository;

@RestController
public class JournalController
{
	@Autowired
	private JournalRepository repo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model)
	{
		model.setViewName("index");
		model.addObject("journal", repo.findAll());
		return model;
	}

	/*@RequestMapping(value = "/login")
	public ModelAndView login(ModelAndView modelAndView)
	{
		modelAndView.setViewName("login");
		return modelAndView;
	}*/

}
