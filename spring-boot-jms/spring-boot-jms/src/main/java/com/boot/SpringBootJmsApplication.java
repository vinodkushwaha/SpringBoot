package com.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;

import com.boot.activemq.Producer;
import com.boot.model.Email;

@SpringBootApplication
public class SpringBootJmsApplication
{
	private Logger log = LoggerFactory.getLogger(SpringBootJmsApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootJmsApplication.class, args);
	}

	@Value("${destination.q}")
	String queue;

	/*@Bean
	InitializingBean sendMessage(Producer producer)
	{
		return () ->
		{
			producer.sendTo(queue, "Spring Boot Rocks!");
		};
	}*/

	/*@JmsListener(destination = "${destination.q}" )
	@SendTo("${myotherqueue}")
	public String  simplerConsumer(String message)
	{
		log.info("Simpler Consumer> " + message);
		return message + " and Spring Messaging too!";

	}

	@JmsListener(destination = "${myotherqueue}")
	public void anotherSimplerConsumer(String message)
	{
		log.info("Another Simpler Consumer> " + message);
	}*/

	@Value("${mailbox}")
	String mailbox;

	@Bean
	InitializingBean sendMailMessage(Producer producer)
	{
		return () ->
		{
			producer.sendMail(mailbox, new Email("info@springboot.com",
			                                     "Hello"));
		};
	}

}
