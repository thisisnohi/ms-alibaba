package nohi.cloud.apone.feign;

import nohi.cloud.apone.feign.intf.EchoServiceFallbackImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author NOHI
 * 2022-06-20 21:48
 **/
@Configuration
public class FeignConfiguration {
    @Bean
    public EchoServiceFallbackImpl echoServiceFallback() {
        return new EchoServiceFallbackImpl();
    }
}
