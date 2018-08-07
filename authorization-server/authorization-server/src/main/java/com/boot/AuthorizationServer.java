package com.boot;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableResourceServer
public class AuthorizationServer
{
	private static final Logger log = LoggerFactory.getLogger(AuthorizationServer.class);

	public static void main(String[] args)
	{
		SpringApplication.run(AuthorizationServer.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal user)
	{
		log.info("AuthorizationServer '/user' has been called");
		log.info("user info: " + user.toString());
		return user;
	}
}
