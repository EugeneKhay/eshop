package com.eshop.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String message) {
        //TODO Change to logger
        System.out.println("Sending message... ");
        jmsTemplate.send(session -> session.createTextMessage(message));
    }
}