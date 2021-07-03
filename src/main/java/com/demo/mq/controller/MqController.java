package com.demo.mq.controller;

import com.demo.mq.producer.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private MqProducer mqProducer;

    @RequestMapping("/queue")
    public void queue() {
        mqProducer.sendQueue();
    }

    @RequestMapping("/topic")
    public void topic() {
        mqProducer.sendTopic();
        mqProducer.sendLikeTopic();
    }
}
