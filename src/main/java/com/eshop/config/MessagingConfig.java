package com.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;


@Configuration
public class MessagingConfig {

    private static final String PRODUCTS_QUEUE = "test";



    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        props.put(Context.SECURITY_PRINCIPAL, "some");
        props.put(Context.SECURITY_CREDENTIALS, "some");
        InitialContext ic = new InitialContext(props);
        ConnectionFactory connectionFactory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
        UserCredentialsConnectionFactoryAdapter cf = new UserCredentialsConnectionFactoryAdapter();
        cf.setUsername("some");
        cf.setPassword("some");
        cf.setTargetConnectionFactory(connectionFactory);
        return cf;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(PRODUCTS_QUEUE);
        return template;
    }

}







