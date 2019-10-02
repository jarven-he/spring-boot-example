package com.jarven.example.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-24 09:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends RuntimeException {

    final Exception ex;

    final String msg;

    public ServiceException(String msg) {
        super(msg);
        this.ex = null;
        this.msg = msg;
    }

    public ServiceException(Exception ex) {
        super(ex);
        this.ex = ex;
        this.msg = ex.getMessage();
    }

    public ResponseVo convertTo() {
        return ResponseVo.fail(msg);
    }
}