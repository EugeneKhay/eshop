package com.eshop.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    private Logger logger = Logger.getLogger("logger");

    public void sendMessage(final String message) {
        logger.info("Sending message");
        jmsTemplate.send(session -> session.createTextMessage(message));
    }
}