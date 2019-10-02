package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019/9/30 9:05 上午
 */

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReduceInventoryDto extends BaseEntity {

    @ApiModelProperty(value = "商品id", required = true)
    private Integer productId;

    @ApiModelProperty(value = "扣减数量", required = true)
    private Integer reduceNum;
}
