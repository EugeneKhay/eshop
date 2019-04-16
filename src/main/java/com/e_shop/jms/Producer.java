package com.e_shop.jms;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

//import com.google.gson.Gson;

@Component
public class Producer {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String queueName, final String message) {
        //Map map = new Gson().fromJson(message, Map.class);
        //final String textMessage = "Hello" + map.get("name");
        final String textMessage = "Hello";
        System.out.println("Sending message " + textMessage + "to queue - " + queueName);
        jmsTemplate.send(queueName, session -> {
            TextMessage message1 = session.createTextMessage();
            return message1;
        });
    }

}
