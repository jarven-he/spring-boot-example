package com.jarven.example.domain.product;

import com.jarven.example.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019/9/8 9:00 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSpec extends BaseEntity {

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "规格值")
    private String value;
}
