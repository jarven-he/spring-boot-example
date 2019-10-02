package com.jarven.example.service;

import com.jarven.example.domain.PageDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Optional;

/**
 * @description:
 * @author: 何佳文
 * @date: 2018-12-31 11:13
 */
public class BaseService {

    /**
     * 获取分页信息
     *
     * @param pageDto 分页条件
     * @return spring data 分页对象
     */
    public PageRequest getPageRequest(PageDto pageDto) {
        return PageRequest.of(Optional.ofNullable(pageDto.getPage()).orElse(1) - 1,
                Optional.ofNullable(pageDto.getSize()).orElse(10));
    }

    PageRequest getPageRequest(PageDto pageDto, Sort sort) {
        return PageRequest.of(Optional.ofNullable(pageDto.getPage()).orElse(1) - 1,
                Optional.ofNullable(pageDto.getSize()).orElse(10), sort);
    }

    private Object get(String key) {
        var servletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return servletRequest.getAttribute(key);
    }


    /**
     * 获取起始条数
     *
     * @param pageDto 分页对啊想
     * @return 起始条数
     */
    long getSkip(PageDto pageDto) {
        return (pageDto.getPage() - 1L) * pageDto.getSize();
    }

    /**
     * 用户ID
     *
     * @return 用户ID
     */
    public String getUserId() {
        return (String) get("userId");
    }


    /**
     * 获取微信用户openId
     *
     * @return openId
     */
    public String getOpenId() {
        return (String) get("openId");
    }
}
