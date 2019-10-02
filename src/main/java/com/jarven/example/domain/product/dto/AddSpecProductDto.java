package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019/9/11 3:51 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddSpecProductDto extends BaseEntity {

    @ApiModelProperty(value = "商品名称", required = true)
    private String productName;

    @ApiModelProperty(value = "分类", required = true)
    private Integer category;

    @ApiModelProperty(value = "0:虚拟商品 1:实物商品", required = true)
    private Short productType;

    @ApiModelProperty(value = "特色介绍")
    private String productDesc;

    @ApiModelProperty(value = "购买须知")
    private String buyDesc;

    @ApiModelProperty(value = "销售状态 0:下架 1:上架", required = true)
    private Integer saleState;

    @ApiModelProperty(value = "标题图片", required = true)
    private String titleImgUrl;

    @ApiModelProperty(value = "分享图片")
    private String shareImgUrl;

    @ApiModelProperty(value = "滚动图片", required = true)
    private List<String> rollingImgUrl;

    @ApiModelProperty(value = "宣传图片")
    private String propagandaImgUrl;

    @ApiModelProperty(value = "规格信息")
    private List<ProductSpecDto> specProduct;
}
