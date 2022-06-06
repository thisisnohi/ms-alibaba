package nohi.cloud.aptwo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author NOHI
 * 2022-06-05 22:30
 **/
@RestController
@Slf4j
public class HelloController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name:}")
    private String appName;

    @Value("${test.conf1:conf1}")
    private String conf1;

    @GetMapping(value = "/")
    public String index() {
        log.info("HelloController.index...");
        return "Hello";
    }

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        log.info("ApServerTwo.conf1:{}", conf1);
        log.info("echo..", str);
        return restTemplate.getForObject("http://apserver-one/echo/" + str, String.class);
//        return restTemplate.getForObject("http://127.0.0.1:8081/echo/" + str, String.class);

        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose("ApServerOne");
//        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
//        log.info("request url:" + url);
//        return restTemplate.getForObject(url, String.class);
    }

//    @GetMapping(value = "/echo/{string}")
//    public String echo(@PathVariable String string) {
//        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose("ApServerOne");
//        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
//        log.info("request url:" + url);
//        return restTemplate.getForObject(url, String.class);
//    }
}
