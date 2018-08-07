package com.boot;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

@Profile("pro3")
@SpringBootApplication
@EnableOAuth2Client
@RestController
@EnableAuthorizationServer
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SocialSimpleApplication3 extends WebSecurityConfigurerAdapter
{
	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	public static void main(String[] args)
	{
		SpringApplication.run(SocialSimpleApplication3.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal principal)
	{
		return principal;
	}

	@RequestMapping("/me")
	public Map<String, String> me(Principal principal)
	{
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
	{

		@Override
		public void configure(HttpSecurity http) throws Exception
		{
			http.antMatcher("/me")
			    .authorizeRequests()
			    .anyRequest()
			    .authenticated();
		}
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
		    .exceptionHandling()
		    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
		    .and()
		    .logout()
		    .logoutSuccessUrl("/")
		    .permitAll()
		    .and()
		    .csrf()
		    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		    .and()
		    .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}

	private Filter ssoFilter()
	{
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(facebook(), "/login/facebook"));

		filter.setFilters(filters);
		return filter;
	}

	private Filter ssoFilter(ClientResources client, String path)
	{
		OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(client.getClient(),
		                                                             oauth2ClientContext);
		facebookFilter.setRestTemplate(facebookTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource()
		                                                                      .getUserInfoUri(),
		                                                                client.getClient()
		                                                                      .getClientId());
		tokenServices.setRestTemplate(facebookTemplate);
		facebookFilter.setTokenServices(tokenServices);
		return facebookFilter;
	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter)
	{
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	@ConfigurationProperties("facebook")
	public ClientResources facebook()
	{
		return new ClientResources();
	}

	class ClientResources
	{

		@NestedConfigurationProperty
		private AuthorizationCodeResourceDetails	client		= new AuthorizationCodeResourceDetails();

		@NestedConfigurationProperty
		private ResourceServerProperties			resource	= new ResourceServerProperties();

		public AuthorizationCodeResourceDetails getClient()
		{
			return client;
		}

		public ResourceServerProperties getResource()
		{
			return resource;
		}
	}
}
