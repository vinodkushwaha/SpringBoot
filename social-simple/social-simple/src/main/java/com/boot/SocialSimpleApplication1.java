package com.boot;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("pro1")
@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SocialSimpleApplication1 extends WebSecurityConfigurerAdapter
{

	public static void main(String[] args)
	{
		SpringApplication.run(SocialSimpleApplication1.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal principal)
	{
		return principal;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.antMatcher("/**")
		    .authorizeRequests()
		    .antMatchers("/", "/login**", "/webjars/**")
		    .permitAll()
		    .anyRequest()
		    .authenticated()
		    
		    
		    .and()
		    .logout()
		    .logoutSuccessUrl("/")
		    .permitAll()
		
		
		     .and()
		    .csrf()
		    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
}
