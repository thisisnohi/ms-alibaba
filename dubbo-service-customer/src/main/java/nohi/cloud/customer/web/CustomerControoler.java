package nohi.cloud.customer.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import nohi.cloud.customer.service.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author NOHI
 * 2022-06-21 22:53
 **/
@RestController
@Slf4j
public class CustomerControoler {
    @Autowired
    private GoodsServiceImpl goodsService;

    @GetMapping(value = "/dubbo/{id}")
    public String dubbo(@PathVariable Long id) {
        log.debug("id:{}", id);
        String retMsg = goodsService.booking(id);
        return "ApOneHelloController Hello " + retMsg;
    }
}
