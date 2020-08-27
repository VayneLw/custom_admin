package com.upc.lw.moudules.security.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/14 16:33
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.login.topic}", consumerGroup = "${mq.login.group}")
public class LoginMessageConsumer implements RocketMQListener<String>  {

    @Override
    public void onMessage(String message) {
        log.info("received message: " + message);
    }
}
