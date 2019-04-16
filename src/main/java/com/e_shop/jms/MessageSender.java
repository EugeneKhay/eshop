//package com.e_shop.jms;
////
////import javax.jms.JMSException;
////import javax.jms.Message;
////import javax.jms.ObjectMessage;
////import javax.jms.Session;
////
////import com.e_shop.domain.Product;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.jms.core.JmsTemplate;
////import org.springframework.jms.core.MessageCreator;
////import org.springframework.stereotype.Component;
////
////@Component
////public class MessageSender {
////
////    @Autowired
////    JmsTemplate jmsTemplate;
////
////    public void sendMessage(final Product product) {
////
////        jmsTemplate.send(session -> {
////            ObjectMessage objectMessage = session.createObjectMessage(product);
////            return objectMessage;
////        });
////    }
////
////    public MessageSender() {
////    }
////}