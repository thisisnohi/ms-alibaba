package nohi.cloud.provider.service;

import lombok.extern.slf4j.Slf4j;
import nohi.cloud.demo.dto.GoodsDetailDTO;
import nohi.cloud.demo.service.GoodsService;
import org.apache.dubbo.config.annotation.DubboService;

import java.math.BigDecimal;

/**
 * @author NOHI
 * 2022-06-21 22:41
 **/
@DubboService
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Override
    public GoodsDetailDTO findGoodsById(Long id) {
        log.debug("findGoodsById:{}", id);
        GoodsDetailDTO goods = new GoodsDetailDTO();
        goods.setGoodsNo(String.valueOf(id));
        goods.setGoodsName("手机");
        goods.setInventory(2);
        goods.setPrice(new BigDecimal("3000"));
        return goods;
    }
}
