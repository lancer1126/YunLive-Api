package com.lance.yunlive.common.exception;

import com.lance.yunlive.common.enums.ResultCode;

public class BadRequestException extends RuntimeException {

    private ResultCode code;

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(ResultCode code, String msg) {
        super(msg);
        this.code = code;
    }
}
