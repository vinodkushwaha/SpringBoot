package com.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.repository.PersonRepository;

@RestController
@SpringBootApplication
public class SpringBootActuatorApplication
{
	private static final Logger log = LoggerFactory.getLogger(SpringBootActuatorApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootActuatorApplication.class, args);
	}

	@Autowired
	CounterService		counter;

	@Autowired
	GaugeService		gaugeService;

	@Autowired
	PersonRepository	repo;

	@RequestMapping("/")
	public String index()
	{
		counter.increment("counter.index.invoked");
		log.info("> Persons in Database: ");
		double startTime = (double) System.currentTimeMillis();
		repo.findAll()
		    .forEach(person -> log.info(person.toString()));
		try
		{
			Thread.currentThread()
			      .sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double endTime = (double) System.currentTimeMillis();
		gaugeService.submit("index-execution-time", endTime - startTime);
		return "Spring Boot Actuator";
	}

	@Bean
	CommandLineRunner findAll(PersonRepository repo)
	{
		return args ->
		{
			log.info("> Persons in Database: ");
			repo.findAll()
			    .forEach(person -> log.info(person.toString()));
		};
	}

}
