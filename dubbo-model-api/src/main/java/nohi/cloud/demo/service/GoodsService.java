package nohi.cloud.demo.service;

import nohi.cloud.demo.dto.GoodsDetailDTO;

/**
* 商品服务
* @author NOHI
* 2022/6/21 22:35
*/
public interface GoodsService {

    /**
     * 通过ID查询商品详情
     * @param id 商品ID
     * @return 返回结果
     */
    GoodsDetailDTO findGoodsById(Long id);
}
