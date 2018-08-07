package com.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		    .antMatchers("/css/**", "/index")
		    .permitAll()
		    .antMatchers("/user/**")
		    .authenticated()
		    // .hasRole("USER")
		    .and()
		    .formLogin()
		    .loginPage("/login")
		    .failureUrl("/login-error");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		// Version 1

		/*
		 * auth.inMemoryAuthentication() .withUser("user") .password("password")
		 * .roles("USER");
		 */

		// Version 2
		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource) .withUser("user")
		 * .password("password") .roles("USER") .and() .withUser("admin")
		 * .password("password") .roles("ADMIN");
		 */

		// Version 3

		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .usersByUsernameQuery("select username,password, enabled from users where username=?")
		    .authoritiesByUsernameQuery("select username, authority from authorities where username=?");

		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource);
		 */

	}

}
