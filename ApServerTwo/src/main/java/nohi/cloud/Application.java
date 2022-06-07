package nohi.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author NOHI
 * 2022-06-05 22:29
 **/
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch(Exception e) {
          log.error("启动异常:{}", e.getMessage(), e);
        }

    }
}
