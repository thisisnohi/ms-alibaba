package nohi.cloud.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author NOHI
 * 2022-06-21 22:36
 **/
@Data
public class GoodsDetailDTO implements Serializable {
    private String goodsNo;
    private String goodsName;
    private Integer inventory;
    private BigDecimal price;
}
