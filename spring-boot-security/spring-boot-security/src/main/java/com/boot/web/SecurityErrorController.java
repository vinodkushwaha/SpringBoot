package com.boot.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityErrorController implements ErrorController
{
	private static final String PATH = "/error";

	@Override
	public String getErrorPath()
	{
		return PATH;
	}

	@RequestMapping(value = PATH)
	public String error()
	{
		return "error";
	}

}
