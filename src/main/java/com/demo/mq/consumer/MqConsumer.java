package com.demo.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqConsumer {

    @JmsListeners(@JmsListener(destination = "queue.test", containerFactory = "queueListenerFactory"))
    public void receiveQueue(String msg) {
        log.info("JmsConsumer receiveQueue msg=[{}]", msg);
    }

    @JmsListeners(@JmsListener(destination = "topic.test", containerFactory = "topicListenerFactory"))
    public void receiveTopic(String msg) {
        log.info("JmsConsumer receiveTopic msg=[{}]", msg);
    }

}
