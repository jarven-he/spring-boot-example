package com.jarven.example.security.domain;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author hejiawen <br>
 * @version 1.0<br>
 * @since V1.0<br>
 */
@Data
@Accessors(chain = true)
public class SecurityUser {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户openId
     */
    private String openId;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型 1:用户 2:管理员
     */
    private Integer userType;

    /**
     * 角色
     */
    private List<JSONObject> roleResource;

    /**
     * 微信授权码
     */
    private String code;

    /**
     * 扩展信息
     */
    private Map<String, Object> extension;
}
