package com.jarven.example.domain.product.dto;

import com.jarven.example.domain.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-08-01 18:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryProductCategoryDto extends PageDto {

    @ApiModelProperty(value = "分类名称")
    private String name;
}
