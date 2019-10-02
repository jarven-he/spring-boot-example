package com.jarven.example.domain.product.vo;

import com.jarven.example.domain.product.ProductSpec;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-05-10 22:02
 */
@Data
@Accessors(chain = true)
public class LinkProductVo {

    @ApiModelProperty(value = "商品Id")
    private Integer productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "销售价格")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品图片")
    private String titleImgUrl;

    @ApiModelProperty(value = "规格")
    private List<ProductSpec> spec;

    @ApiModelProperty(value = "销售状态 0:下架 1:上架")
    private Integer saleState;
}
