 package com.boot.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerOld implements MessageListener
{
	private Logger log = LoggerFactory.getLogger(ConsumerOld.class);

	@Override
	public void onMessage(Message message)
	{
		try
		{
			log.info("Old Consumer> " + ((TextMessage) message).getText());
		}
		catch (JMSException ex)
		{
			ex.printStackTrace();
		}

	}

}
