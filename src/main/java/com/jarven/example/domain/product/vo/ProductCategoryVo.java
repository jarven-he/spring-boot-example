package com.jarven.example.domain.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-08-01 17:48
 */
@Data
public class ProductCategoryVo {

    @ApiModelProperty(value = "分类Id")
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类图片")
    private String img;

}
