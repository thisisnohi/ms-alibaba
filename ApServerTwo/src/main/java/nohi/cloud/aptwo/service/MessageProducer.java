package nohi.cloud.aptwo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author NOHI
 * 2022-06-27 21:08
 **/
@Service
@Slf4j
public class MessageProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void abc(String args) {
        log.debug("args:", args);
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
    }

}
