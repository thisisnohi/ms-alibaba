package nohi.cloud.aptwo.web;

import lombok.extern.slf4j.Slf4j;
import nohi.cloud.aptwo.config.ApConfig;
import nohi.cloud.aptwo.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author NOHI
 * 2022-06-05 22:30
 **/
@RestController
@Slf4j
@RefreshScope
public class ApTwoHelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    RestTemplate restTemplateNoLa;
    @Value("${spring.application.name:}")
    private String appName;
    @Value("${test.conf1:conf1}")
    private String conf1;
    @Autowired
    private ApConfig apConfig;
    @Autowired
    private MessageProducer messageProducer;

    @GetMapping(value = "/")
    public String index() {
        log.info("HelloController.index...{}", apConfig.toString());
        return "Hello " + apConfig.toString();
    }

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        log.info("ApServerTwo.conf1:{}", conf1);
        log.info("echo..{}", str);

//        if ("1".equalsIgnoreCase("1")) {
//            throw new RuntimeException("人工异常");
//        }
        // 方式一
        String resp = restTemplate.getForObject("http://apserver-one/echo/" + str, String.class);
        log.info("resp..{}", resp);

        // 方式二
        // 使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("apserver-one");
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
        log.info("request url:" + url);
        return "Cur[AP-TWO] [AP-ONE]响应:" + restTemplateNoLa.getForObject(url, String.class);
    }

    /**
     * rocketmq 生产消息
     * @param msg
     * @return
     */
    @RequestMapping(value = "/messageProducer/{msg}", method = RequestMethod.GET)
    public String messageProducer(@PathVariable String msg) {
        log.info("msg..{}", msg);
        messageProducer.abc(msg == null ? " is null" : msg);
        log.info("发送结束");
        return "OK";
    }

}
