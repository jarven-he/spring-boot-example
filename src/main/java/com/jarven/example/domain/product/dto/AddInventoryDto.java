package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-08-15 14:57
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AddInventoryDto extends BaseEntity {

    @ApiModelProperty(value = "商品Id")
    private Integer productId;

    @ApiModelProperty(value = "商品Id")
    private Integer inventory;
}
