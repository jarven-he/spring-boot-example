package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019/9/23 5:26 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryPointProductPageDto extends PageDto {

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("分类id")
    private Integer category;

    @ApiModelProperty("销售状态")
    private Integer saleState;
}
