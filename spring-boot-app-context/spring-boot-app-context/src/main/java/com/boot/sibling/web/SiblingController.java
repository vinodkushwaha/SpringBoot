package com.boot.sibling.web;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiblingController
{
	// Cannot wire bean not in context
	@Autowired(required = false)
	@Qualifier("child_ctx_bean")
	private String	child;

	@Autowired
	@Qualifier("parent_ctx_bean")
	private String	parent;

	@RequestMapping("/sib-date")
	public void home(Writer writer) throws IOException
	{
		System.out.println("Parent bean in sibling " + parent);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.now();
		String formattedDate = date.format(formatter);
		writer.write(formattedDate);
	}
}
