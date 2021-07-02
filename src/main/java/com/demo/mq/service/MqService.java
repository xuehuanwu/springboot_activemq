package com.demo.mq.service;

public interface MqService {

    /**
     * 发送队列(点对点,不可重复消费)
     *
     * @param queueName
     * @param msg
     * @return
     */
    boolean sendQueue(String queueName, String msg);

    /**
     * 发送主题(发布/订阅,可以重复消费)
     *
     * @param topicName
     * @param msg
     * @return
     */
    boolean sendTopic(String topicName, String msg);
}
