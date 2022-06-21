package nohi.cloud.apone.feign.intf;

import nohi.cloud.apone.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *  feign client
 * @author nohi
 */
@FeignClient(name = "apserver-two", fallback = EchoServiceFallbackImpl.class, configuration = FeignConfiguration.class)
public interface EchoService {
    /**
     * 回显现
     * @param str 参数
     * @return 返回
     */
    @GetMapping(value = "/echo/{str}")
    String echo(@PathVariable("str") String str);
}
