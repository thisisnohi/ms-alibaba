package nohi.cloud.customer.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import nohi.cloud.demo.dto.GoodsDetailDTO;
import nohi.cloud.demo.service.GoodsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author NOHI
 * 2022-06-21 22:48
 **/
@Service
@Slf4j
public class GoodsServiceImpl {
    /**
     * 要调用的远程服务
     */
    @DubboReference
    private GoodsService goodsService;

    public String booking(Long id) {
        GoodsDetailDTO goods = this.goodsService.findGoodsById(id);
        log.debug("goods:{}", JSONObject.toJSONString(goods));
        return goods.getGoodsName();
    }
}
