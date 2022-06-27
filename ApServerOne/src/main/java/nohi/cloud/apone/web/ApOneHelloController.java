package nohi.cloud.apone.web;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import nohi.cloud.apone.config.ApConfig;
import nohi.cloud.apone.feign.intf.EchoService;
import nohi.cloud.apone.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NOHI
 * 2022-06-05 22:30
 **/
@RestController
@Slf4j
@RefreshScope
public class ApOneHelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    RestTemplate restTemplateNoLa;
    @Value("${test.conf1:conf1}")
    private String conf1;
    @Autowired
    private ApConfig apConfig;
    @Autowired
    private EchoService echoService;

    @Resource
    private MessageProducer messageProducer;

    private final String GET_HELLO = "getHello";

    @GetMapping(value = "/")
    @SentinelResource("apOne主页")
    public String index() {
        log.info("HelloController.index...{}", apConfig.toString());
        return "ApOneHelloController Hello " + apConfig.toString();
    }

    /**
     * 自定义限流规则
     * @return sring
     */
    @GetMapping(value = "/testRule")
    public String testRule() {
        // 使用限流规则，保护”业务逻辑“
        try(Entry entry = SphU.entry(GET_HELLO)) {
            log.info("apOne.testRule OK");
            // 正常的业务逻辑
            return "OK testRule";
        } catch (Exception e) {
            log.error("当前请求被限流了:{}",  e.getMessage());
            // 降级方案
            return "系统繁忙，请稍后再试！";
        }
    }

    /**
     * 自定义限流规则
     * @return string
     */
    @GetMapping(value = "/testRule2")
    public String testRule2() {
        if (SphO.entry(GET_HELLO)) {
            try {
                log.info("apOne.testRule2 OK");
                // 正常的业务逻辑
                return "Hi testRule2";
            } finally {
                SphO.exit();
            }
        } else {
            log.info("当前请求被限流了！");
            // 降级方案
            return "系统繁忙，请稍后再试！";
        }
    }


    /**
     * 定义隔离规则
     * 当前类的构造函数执行之后执行该方法
     */
    @PostConstruct
    public void initFlowRule() {
        List<FlowRule> ruleList = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 设置资源名称
        rule.setResource("apOne.testRule");
        // 指定限流模式为QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 指定QPS限流阈值
        rule.setCount(2);
        ruleList.add(rule);
        // 加载该规则
        FlowRuleManager.loadRules(ruleList);
    }

    /**
     * 限流响应信息
     * @param blockException ex
     * @return string
     */
    public String exceptionHandler(BlockException blockException){
        // 降级方案
        log.info("当前请求被限流了：",blockException);
        return "exceptionHandler 系统繁忙，请稍后再试！";
    }
    @SentinelResource(value = "apOne回显", blockHandler = "exceptionHandler")
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        log.info("ApServerTwo.conf1:{}", conf1);
        log.info("ApServerTwo.str:{}", str);
        return "Cur[AP-TWO]" + str;
    }

    @RequestMapping(value = "/echoFromTwo/{str}", method = RequestMethod.GET)
    @SentinelResource("apOne请求apTwo")
    public String echoFromTwo(@PathVariable String str) {
        log.info("ApServerOne.conf1:{}", conf1);
        log.info("echo..{}", str);
        // 方式一
        String resp = restTemplate.getForObject("http://apserver-two/echo/" + str, String.class);
        log.info("resp..{}", resp);
        return "Cur[AP-ONE].echoFromTwo [AP-TWO]响应:" + resp;
    }

    @RequestMapping(value = "/echoFromTwoWithFeignClient/{str}", method = RequestMethod.GET)
    public String echoFromTwoWithFeignClient(@PathVariable String str) {
        log.info("ApServerOne.echoFromTwoWithFeignClient.conf1:{}", conf1);
        log.info("echo..{}", str);
        String resp = echoService.echo(str);
        log.info("resp..{}", resp);
        return "Cur[AP-ONE].echoFromTwoWithFeignClient [AP-TWO]响应:" + resp;
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
