package com.jarven.example.security.domain;

import java.util.Arrays;

/**
 * @author hejiawen <br>
 * @version 1.0<br>
 * @since V1.0<br>
 */
public enum RoleEnum {

    //会员
    CUSTOMER(1, "ROLE_CUSTOMER"),

    //网站归属者
    ADMIN(2, "ROLE_ADMIN");

    private Integer key;

    private String value;

    RoleEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getRole(Integer userType) {
        return Arrays.stream(RoleEnum.values()).filter(enu -> enu.getKey().equals(userType))
                .findFirst().map(RoleEnum::getValue).orElse(null);
    }
}
