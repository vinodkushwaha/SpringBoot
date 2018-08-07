package com.boot.child.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChildController
{
	// Cannot wire bean not in context

	@Autowired(required = false)
	@Qualifier("sibling_ctx_bean")
	private String	sibling;

	@Autowired
	@Qualifier("parent_ctx_bean")
	private String	parent;

	@RequestMapping("/child-date")
	public String home(Model model)
	{
		System.out.println("Parent bean: " + parent);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.now();
		String formattedDate = date.format(formatter);
		model.addAttribute("serverDate", formattedDate);
		return "home";
	}
}
