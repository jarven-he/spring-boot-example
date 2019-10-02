package com.jarven.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019/9/23 5:11 下午
 */
@Data
@NoArgsConstructor
public class DefaultProperty {

    private Integer deleteFlag = 0;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime updateTime = LocalDateTime.now();
}
