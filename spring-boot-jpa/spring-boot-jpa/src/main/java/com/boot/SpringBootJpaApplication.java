package com.boot;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.boot.domain.Journal;
import com.boot.service.JournalService;

@SpringBootApplication
public class SpringBootJpaApplication
{
	private static final Logger log = LoggerFactory.getLogger(SpringBootJpaApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner start(JournalService service)
	{
		return args ->
		{
			log.info("JournalService instance: " + service.getClass()
			                                              .getName());
			log.info("@@ Inserting Data....");
			service.insertData();
			log.info("@@ findAll() call...");
			service.findAll()
			       .forEach(entry -> log.info(entry.toString()));

			log.info("@@ findByCreatedAfter(Date date) call...");
			service.findByCreatedAfter(new SimpleDateFormat("dd-MM-yyyyy").parse("01-01-2016"))
			       .forEach(entry -> log.info(entry.toString()));
			log.info("@@ findByCustomTitleQuery(String word) call...");
			service.findByCustomTitleQuery("Reading")
			       .forEach(entry -> log.info(entry.toString()));
			log.info("@@ findAllByEntityManager() call...");
			service.findAllByEntityManager()
			       .forEach(entry -> log.info(entry.toString()));
			log.info("@@ findByCreated() call...");
			Journal journal = service.findByCreated(new SimpleDateFormat("dd-MM-yyyyy").parse("01-01-2016"));
			log.info(journal.toString());
		};
	}
}
