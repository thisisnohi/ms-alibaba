package nohi.cloud.apone.web;

import lombok.extern.slf4j.Slf4j;
import nohi.cloud.apone.config.ApConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NOHI
 * 2022-06-05 22:30
 **/
@RestController
@Slf4j
@RefreshScope
public class HelloController {
    @Value("${user.name}")
    String userName;

    @Value("${user.age}")
    int age;

    @Autowired
    private ApConfig apConfig;
    @GetMapping(value = "/")
    public String index() {
        log.info("HelloController.index...");
        log.info("apConfig:{}", null == apConfig ? "is null" : apConfig.toString());
        log.info("userName:{} age:{}", userName, userName);
        return "Hello " + userName + ", age:" + age + "\n" + apConfig.toString();
    }

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        log.info("ApServerOne.HelloController.echo...{}", string);
        return "Hello Nacos Discovery, ApServerOne " + string;
    }
}
