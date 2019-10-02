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
 * @date: 2019-04-29 14:17
 */
@Data
@Accessors(chain = true)
public class ProductVo {

    @ApiModelProperty(value = "商品Id")
    private Integer productId;

    @ApiModelProperty(value = "商品类型: 0:虚拟商品 1:实物商品")
    private Short productType;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "销售价格")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "推广佣金")
    private BigDecimal popularizeAmount;

    @ApiModelProperty(value = "团长佣金")
    private BigDecimal popularizeColonelAmount;

    @ApiModelProperty(value = "已售")
    private Integer ticketCount;

    @ApiModelProperty(value = "库存")
    private Long inventory;

    @ApiModelProperty(value = "销售状态 0:下架 1:上架")
    private Integer saleState;

    @ApiModelProperty(value = "排序权重")
    private Integer sortWeight;

    @ApiModelProperty(value = "标题图片")
    private String titleImgUrl;

    @ApiModelProperty(value = "分享图片")
    private String shareImgUrl;

    @ApiModelProperty(value = "是否前端展示 0:不展示 1 展示")
    private Integer showList;

    @ApiModelProperty(value = "商品的规格")
    private List<ProductSpec> spec;
}
