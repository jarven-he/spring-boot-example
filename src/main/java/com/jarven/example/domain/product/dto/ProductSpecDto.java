package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.BaseEntity;
import com.jarven.example.domain.product.ProductSpec;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019/9/11 3:53 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSpecDto extends BaseEntity {

    @ApiModelProperty(value = "销售价格", required = true)
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价", required = true)
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "库存", required = true)
    private Integer inventory;

    @ApiModelProperty(value = "规格信息", required = true)
    private List<ProductSpec> spec;
}
