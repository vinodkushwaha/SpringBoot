package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class SpringBootJournalApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}
}
