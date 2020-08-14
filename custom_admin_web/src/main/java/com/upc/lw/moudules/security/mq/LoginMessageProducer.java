package com.upc.lw.moudules.security.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/14 16:32
 */
@Component
@Slf4j
public class LoginMessageProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${login.topic}")
    private String topic;

    public void sendMessage(String content) {
        log.info("produce topic:{},message:{}", topic, content);
        try {
            rocketMQTemplate.convertAndSend(topic, content);
        } catch (Exception e) {
            log.error("send message error:", e);
        }
    }
}
