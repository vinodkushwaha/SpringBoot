package com.boot.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.boot.model.Email;

@Component
public class Consumer
{
	private Logger log = LoggerFactory.getLogger(Consumer.class);

	/*@JmsListener(destination = "${destination.q}")
	public void receive(String message)
	{
		log.info("Received message='{}'", message);
	}*/

	@JmsListener(destination = "${mailbox}")
	public void receiveMessage(Email email)
	{
		log.info("Received Email >" + email + ">");
	}
}
