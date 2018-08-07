package com.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
@EnableGlobalAuthentication
public class JdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter
{

	@Autowired
	private DataSource dataSource;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception
	{

		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .usersByUsernameQuery("select username,password, enabled from users where username=?")
		    .authoritiesByUsernameQuery("select username, authority from authorities where username=?");

	}

}
