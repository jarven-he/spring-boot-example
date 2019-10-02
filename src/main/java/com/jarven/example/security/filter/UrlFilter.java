package com.jarven.example.security.filter;

import com.jarven.example.security.domain.SecurityUser;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: 何佳文
 * @date: 2018/11/4 10:00
 */
@Component
public class UrlFilter {


    /**
     * 允许不验证的请求,默认都无需认证
     *
     * @return 无需认证的请求
     */
    public String[] permitURL() {
        return new String[]{
                "/v2/api-docs",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-resources/**"
        };
    }

    /**
     * 哪个请求负责认证用户信息
     *
     * @return 负责认证的请求
     */
    public String identifyURL() {
        return "login";
    }

    /**
     * 用于返回认证结果
     *
     * @param user 用户信息
     * @return 认证结果
     */
    boolean identifyCallBack(SecurityUser user) {
        //todo 此处添加自己的认证代码
        return true;
    }
}
