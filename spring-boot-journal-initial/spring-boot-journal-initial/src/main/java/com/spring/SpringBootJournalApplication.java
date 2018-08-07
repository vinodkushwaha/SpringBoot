package com.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.config.ConfigProperties;
import com.spring.domain.Journal;
import com.spring.repository.JournalRepository;

@SpringBootApplication
public class SpringBootJournalApplication
{
	private static final Logger log = LoggerFactory.getLogger(SpringBootJournalApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootJournalApplication.class, args);

	}

	@Bean
	InitializingBean saveData(JournalRepository repo)
	{
		return () ->
		{
			repo.save(new Journal("Get to know Spring Boot",
			                      "Today I will learn Spring Boot",
			                      "01/12/2016"));
			repo.save(new Journal("Simple Spring Boot Project",
			                      "I will do my first Spring Boot Project",
			                      "25/02/2016"));
			repo.save(new Journal("Spring Boot Reading",
			                      "Read more about Spring Boot",
			                      "12/01/2016"));
			repo.save(new Journal("Spring Boot in the Cloud",
			                      "Spring Boot using Cloud Foundry",
			                      "30/11/2016"));
		};
	}
	
	
	
	

	@Bean
	CommandLineRunner myMethod()
	{
		return args ->
		{
			log.info("CommandLine2 running");
			for (String arg : args)
			{
				log.info(arg);
			}

		};
	}

	@Value("${data.server}")
	private String server;

	@Bean
	ApplicationRunner somemethod()
	{
		return args ->
		{
			if (args.containsOption("enable"))
			{
				log.info("You are enabled");
			}
			log.info("Server: " + server);
		};
	}

	@Value("${myapp.server-ip}")
	String				serverIp;

	@Autowired
	ConfigProperties	props;

	@Bean
	CommandLineRunner values()
	{
		return args ->
		{
			log.info(" > The Server IP is: " + serverIp);
			log.info(" > App Name: " + props.getName());
			log.info(" > App Info: " + props.getDescription());
		};
	}

	/*
	 * extends SpringBootServletInitializer
	 * 
	 * @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) 
	 * { 
	 * 		return builder.sources(SpringBootJournalApplication.class); 
	 * }
	 */
	
	/*SpringApplication app = new SpringApplication(SpringBootFeaturesApplication.class);
	app.setBanner(new Banner()
	{
		@Override
		public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out)
		{
			out.print("\n\n\tThis is my own banner!\n\n".toUpperCase());
		}
	});
	app.run(args);*/

}
