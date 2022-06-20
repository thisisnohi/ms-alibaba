package nohi.cloud.apotwo.web;

import lombok.extern.slf4j.Slf4j;
import nohi.cloud.apotwo.config.ApConfig;
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
public class HelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    RestTemplate restTemplateNoLA;
    @Value("${spring.application.name:}")
    private String appName;
    @Value("${test.conf1:conf1}")
    private String conf1;
    @Autowired
    private ApConfig apConfig;

    @GetMapping(value = "/")
    public String index() {
        log.info("HelloController.index...{}", apConfig.toString());
        return "Hello " + apConfig.toString();
    }

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        log.info("ApServerTwo.conf1:{}", conf1);
        log.info("ApServerTwo.str:{}", str);
        return "Cur[AP-TWO]" + str;
    }

    @RequestMapping(value = "/echoFromTwo/{str}", method = RequestMethod.GET)
    public String echoFromTwo(@PathVariable String str) {
        log.info("ApServerTwo.conf1:{}", conf1);
        log.info("echo..{}", str);
        // 方式一
        String resp = restTemplate.getForObject("http://apserver-two/echo/" + str, String.class);
        log.info("resp..{}", resp);
        return "Cur[AP-ONE].echoFromTwo [AP-TWO]响应:" + resp;
    }

}
