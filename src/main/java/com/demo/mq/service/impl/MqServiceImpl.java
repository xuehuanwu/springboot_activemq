package com.demo.mq.service.impl;

import com.demo.mq.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MqServiceImpl implements MqService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public boolean sendQueue(String queueName, String msg) {
        log.info("MqServiceImpl sendQueue name=[{}], msg=[{}]", queueName, msg);
        if (StringUtils.isBlank(queueName) || StringUtils.isBlank(msg)) {
            return false;
        }
        try {
            jmsMessagingTemplate.convertAndSend(queueName, msg);
            return true;
        } catch (Exception e) {
            log.error("sendQueue error. queueName:{}, msg:{}.", queueName, msg, e);
        }
        return false;
    }

    @Override
    public boolean sendTopic(String topicName, String msg) {
        log.info("MqServiceImpl sendTopic name=[{}], msg=[{}]", topicName, msg);
        if (StringUtils.isBlank(topicName) || StringUtils.isBlank(msg)) {
            return false;
        }
        try {
            jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(topicName), msg);
            return true;
        } catch (Exception e) {
            log.error("sendQueue error. topicName:{}, msg:{}.", topicName, msg, e);
        }
        return false;
    }
}
