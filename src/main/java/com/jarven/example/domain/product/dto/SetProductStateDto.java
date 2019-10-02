package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-05-01 19:02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SetProductStateDto extends BaseEntity {

    @ApiModelProperty(value = "商品Id")
    private Integer productId;

    @ApiModelProperty(value = "销售状态 0:下架 1:上架")
    private Integer saleState;
}
