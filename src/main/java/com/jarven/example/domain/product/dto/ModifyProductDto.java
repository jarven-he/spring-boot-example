package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-30 16:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModifyProductDto extends BaseEntity {

    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品标签")
    private String tag;

    @ApiModelProperty(value = "分类")
    private Integer category;

    @ApiModelProperty(value = "商品规格")
    private String spec;

    @ApiModelProperty(value = "销售价格")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "特色介绍")
    private String productDesc;

    @ApiModelProperty(value = "购买须知")
    private String buyDesc;

    @ApiModelProperty(value = "推广佣金")
    private BigDecimal popularizeAmount;

    @ApiModelProperty(value = "团长佣金")
    private BigDecimal popularizeColonelAmount;

    @ApiModelProperty(value = "标题图片")
    private String titleImgUrl;

    @ApiModelProperty(value = "分享图片")
    private String shareImgUrl;

    @ApiModelProperty(value = "滚动图片")
    private List<String> rollingImgUrl;

    @ApiModelProperty(value = "宣传图片")
    private String propagandaImgUrl;

    @ApiModelProperty(value = "官方群二维码")
    private String qrCodeUrl;

    @ApiModelProperty(value = "排序权重")
    private Integer sortWeight = 0;

    @ApiModelProperty(value = "是否前端展示 0:不展示 1 展示")
    private Integer showList;
}
