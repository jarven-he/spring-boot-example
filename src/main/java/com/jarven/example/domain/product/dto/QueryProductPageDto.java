package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-29 14:24
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QueryProductPageDto extends PageDto {

    @ApiModelProperty(value = "分类")
    private Integer category;

    @ApiModelProperty(value = "商品状态 0:下架 1:上架")
    private Integer saleState;

    @ApiModelProperty(value = "商品类型: 0:虚拟商品 1:实物商品")
    private Short productType;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "搜索条件")
    private String searchText;

}
