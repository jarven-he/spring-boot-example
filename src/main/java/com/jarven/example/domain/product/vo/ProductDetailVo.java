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
 * @date: 2019-05-02 06:36
 */
@Data
@Accessors(chain = true)
public class ProductDetailVo {

    @ApiModelProperty(value = "商品Id")
    private Integer productId;

    @ApiModelProperty(value = "商品类型: 0:虚拟商品 1:实物商品")
    private Short productType;

    @ApiModelProperty(value = "分类")
    private Integer category;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "销售价格")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "商品标签")
    private String tag;

    @ApiModelProperty(value = "特色介绍")
    private String productDesc;

    @ApiModelProperty(value = "购买须知")
    private String buyDesc;

    @ApiModelProperty(value = "推广佣金")
    private BigDecimal popularizeAmount;

    @ApiModelProperty(value = "团长佣金")
    private BigDecimal popularizeColonelAmount;

    @ApiModelProperty(value = "最早游玩日期")
    private String startDate;

    @ApiModelProperty(value = "已售")
    private Integer ticketCount;

    @ApiModelProperty(value = "库存")
    private Long inventory;

    @ApiModelProperty(value = "销售状态 0:下架 1:上架")
    private Integer saleState;

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
    private Integer sortWeight;

    @ApiModelProperty(value = "推荐商品")
    private List<RecommendProductVo> recommendProduct;

    @ApiModelProperty(value = "是否收藏: 1:是 0:否")
    private Integer focusState;

    @ApiModelProperty(value = "是否前端展示 0:不展示 1 展示")
    private Integer showList;

    @ApiModelProperty(value = "商品的规格")
    private List<ProductSpec> spec;

    @ApiModelProperty(value = "关联商品")
    private List<LinkProductVo> linkProduct;
}
