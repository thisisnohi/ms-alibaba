package nohi.cloud;

import com.alibaba.cloud.dubbo.autoconfigure.DubboServiceRegistrationAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author NOHI
 * 2022-06-05 22:29
 **/
@SpringBootApplication(exclude = DubboServiceRegistrationAutoConfiguration.class)
@EnableDiscoveryClient
@Slf4j
public class DubboProviderApplication {
    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger","slf4j");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DubboProviderApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        log.info("userName:{},userAge:{}", userName, userAge);
    }
}
