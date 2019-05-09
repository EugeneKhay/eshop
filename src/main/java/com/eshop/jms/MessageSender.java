package com.eshop.jms;
////
////import javax.jms.JMSException;
////import javax.jms.Message;
////import javax.jms.ObjectMessage;
////import javax.jms.Session;
////
////import com.eshop.domain.Product;
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







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String message) {
        System.out.println("Sending message... ");
        jmsTemplate.send(session -> session.createTextMessage(message));
    }

}