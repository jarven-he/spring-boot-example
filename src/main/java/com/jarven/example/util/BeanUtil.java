package com.jarven.example.util;

import com.jarven.example.domain.ServiceException;
import org.springframework.beans.BeanUtils;

/**
 * @description:
 * @author: 何佳文
 * @date: 2018/11/01 17:21
 */
public final class BeanUtil {

    private BeanUtil() {
    }

    public static <T> T copyProperties(Class<T> clazz, Object source) {
        try {
            var result = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, result);
            return result;
        } catch (Exception e) {
            throw new ServiceException("属性copy失败");
        }
    }

    public static <T> T copyProperties(Object source, T target) {
        try {
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new ServiceException("属性copy失败");
        }
    }
}

