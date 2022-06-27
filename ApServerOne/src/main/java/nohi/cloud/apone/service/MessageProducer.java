package nohi.cloud.apone.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author NOHI
 * 2022-06-27 13:24
 **/
@Service
public class MessageProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    public void abc(String... args) {
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
    }

}
