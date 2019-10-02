package com.jarven.example.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-24 09:56
 */
@Data
@AllArgsConstructor
public final class ResponseVo<T> implements Serializable {

    private static final long serialVersionUID = -1472019435018714147L;

    /**
     * 调用成功标识
     *
     * @param data 要返回的数据
     */
    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(data, true, 0, "调用成功");
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(null, true, 0, "调用成功");
    }

    /**
     * 调用失败标识
     *
     * @param code    异常码
     * @param message 异常信息
     */
    public static <T> ResponseVo<T> fail(int code, String message) {
        return new ResponseVo<>(null, false, code, Optional.ofNullable(message).orElse("调用失败"));
    }

    public static <T> ResponseVo<T> fail(String message) {
        return new ResponseVo<>(null, false, null, Optional.ofNullable(message).orElse("调用失败"));
    }

    public static <T> ResponseVo<T> fail() {
        return new ResponseVo<>(null, false, null, "系统错误");
    }

    @ApiModelProperty(value = "接口返回数据")
    private T data;

    @ApiModelProperty(value = "接口状态")
    private Boolean success;

    @ApiModelProperty(value = "接口错误码")
    private Integer code;

    @ApiModelProperty(value = "接口消息")
    private String message;

}
