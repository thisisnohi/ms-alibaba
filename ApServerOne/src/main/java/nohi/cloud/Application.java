package nohi.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author NOHI
 * 2022-06-05 22:29
 **/
@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class Application {
    public static void main(String[] args) {
        try(ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args)){
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            log.info("userName:{},userAge:{}", userName, userAge);
        }
    }
}
