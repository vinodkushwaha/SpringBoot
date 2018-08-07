package com.spring.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.domain.JournalEntry;
import com.spring.repository.JournalRepository;

@Controller
public class JournalController
{

	private static final String	VIEW_INDEX	= "index";

	@Autowired
	JournalRepository			repo;

	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public
	 * ModelAndView index(ModelAndView modelAndView) {
	 * modelAndView.setViewName(VIEW_INDEX); modelAndView.addObject("journal",
	 * repo.findAll()); return modelAndView; }
	 */

}
