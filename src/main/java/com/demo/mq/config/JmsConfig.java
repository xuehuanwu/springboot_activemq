package com.demo.mq.config;

import com.demo.mq.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.BytesMessage;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Configuration
public class JmsConfig {

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        factory.setMessageConverter(new SimpleMessageConverter() {
            @Override
            public Object fromMessage(Message message) throws JMSException, MessageConversionException {
                if (message instanceof BytesMessage) {
                    BytesMessage bytesMessage = (BytesMessage) message;
                    try {
                        int length = (int) bytesMessage.getBodyLength();
                        byte[] bytes = new byte[length];
                        bytesMessage.readBytes(bytes);
                        return new String(bytes, Constants.UTF_8);
                    } catch (Exception e) {
                        log.error("convert message error. message:{}.", message, e);
                    }
                }
                return super.fromMessage(message);
            }
        });
        return factory;
    }

}
