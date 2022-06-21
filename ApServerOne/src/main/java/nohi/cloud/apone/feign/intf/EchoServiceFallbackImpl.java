package nohi.cloud.apone.feign.intf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author NOHI
 * 2022-06-20 21:48
 **/
@Slf4j
public class EchoServiceFallbackImpl implements EchoService {
    @Override
    public String echo(@PathVariable("str") String str) {
        log.warn("EchoServiceFallback..echo");
        return "echo fallback";
    }
}
