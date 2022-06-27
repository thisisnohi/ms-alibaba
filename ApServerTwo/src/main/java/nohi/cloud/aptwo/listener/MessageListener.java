package nohi.cloud.apone.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author NOHI
 * 2022-06-27 13:25
 **/
@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "cloud-alibaba-group")
public class MessageListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("接收到请求: {}", message);
    }
}
