package com.boot.sibling;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@PropertySource("classpath:sibling.properties")

// @Configuration
// @ComponentScan
// @EnableWebMvc
// @PropertySource("classpath:application.properties")
public class SiblingCtxConfig
{
	@Bean(name = "sibling_ctx_bean")
	public String getSiblingBean()
	{
		return "sibling_ctx_bean";
	}
}
