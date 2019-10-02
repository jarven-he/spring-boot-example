package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-06-07 21:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecommendProductDto extends BaseEntity {

    @ApiModelProperty(value = "主商品Id")
    private Integer productId;

    @ApiModelProperty(value = "关联商品Id")
    private Set<Integer> recommendProductIds;
}
