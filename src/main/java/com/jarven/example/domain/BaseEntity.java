package com.jarven.example.domain;

import com.jarven.example.util.BeanUtil;
import lombok.Data;

import java.io.Serializable;


/**
 * @description:
 * @author: 何佳文
 * @date: 2018-12-31 11:13
 */
@Data
public class BaseEntity implements Serializable {

    public <T> T convertTo(Class<T> clazz) {
        return BeanUtil.copyProperties(new DefaultProperty(), BeanUtil.copyProperties(clazz, this));
    }

    public <T> T convertTo(T target) {
        BeanUtil.copyProperties(this, target);
        return target;
    }
}


