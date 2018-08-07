package com.boot.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{
	@RequestMapping("/")
	public String root()
	{
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index()
	{
		return "index";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/user/index")
	public String userIndex()
	{
		return "user/index";
	}

	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model)
	{
		model.addAttribute("loginError", true);
		return "login";
	}

}
