package com.boot.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.boot.activemq.ConsumerOld;
import com.boot.activemq.Producer;
import com.boot.model.Email;

@Configuration
public class MessagingConfig
{
	private static final Logger	log	= LoggerFactory.getLogger(MessagingConfig.class);

	@Value("${spring.activemq.broker-url}")
	String						brokerUrl;

	@Value("${spring.activemq.user}")
	String						userName;

	@Value("${spring.activemq.password}")
	String						password;

	@Value("${destination.q}")
	private String				queue;

	@Bean
	public ConnectionFactory connectionFactory()
	{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerUrl);
		connectionFactory.setUserName(userName);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}

	/*@Bean
	public DefaultMessageListenerContainer messageListener()
	{
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		log.info(connectionFactory().getClass()
		                            .getName());
		container.setDestinationName(queue);
		container.setMessageListener(new ConsumerOld());
		return container;
	}*/

	@Bean // Serialize message content to json using TextMessage public
	MessageConverter jacksonJmsMessageConverter()
	{
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("Email");
		return converter;
	}

}
