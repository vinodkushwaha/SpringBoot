package com.boot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@Profile("inmemory")
@Configuration
@EnableGlobalAuthentication
public class InMemorySecurityConfiguration
{

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
		    .withUser("user")
		    .password("password")
		    .roles("USER")
		    .and()
		    .withUser("admin")
		    .password("password")
		    .roles("USER", "ADMIN");
	}
}