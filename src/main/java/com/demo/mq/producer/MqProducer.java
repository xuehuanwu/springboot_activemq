package com.demo.mq.producer;

import com.demo.mq.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqProducer {

    @Autowired
    private MqService mqService;

    public void sendQueue(){
        mqService.sendQueue("queue.test", "send queue test!");
    }

    public void sendTopic(){
        mqService.sendTopic("topic.test", "send topic test!");
    }

    public void sendLikeTopic(){
        mqService.sendTopic("topic.like.test", "send topic like test!");
    }
}
