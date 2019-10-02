package com.jarven.example.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-25 17:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PageDto extends BaseEntity {

    @ApiModelProperty(value = "页码", position = 99)
    private Integer page = 1;

    @ApiModelProperty(value = "每页条数", position = 100)
    private Integer size = 10;

}
