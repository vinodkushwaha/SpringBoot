package com.boot.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

import com.boot.model.Email;

@Component
public class Producer
{
	private static final Logger				log	= LoggerFactory.getLogger(Producer.class);

	@Autowired
	private JmsTemplate						jmsTemplate;

	@Autowired
	private MappingJackson2MessageConverter	jacksonJmsMessageConverter;

	/*public void sendTo(String queue, String message)
	{
		jmsTemplate.setMessageConverter(new SimpleMessageConverter());
		this.jmsTemplate.convertAndSend(queue, message);
		log.info("Producer> Message Sent");
	}*/

	public void sendMail(String mailbox, Email email)
	{
		this.jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
		this.jmsTemplate.convertAndSend(mailbox, email);
		log.info("Producer for JSon> Message Sent");
	}
}
