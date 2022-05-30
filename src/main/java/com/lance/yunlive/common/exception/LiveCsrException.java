package com.lance.yunlive.common.exception;

import com.lance.yunlive.common.enums.ResultCode;

public class LiveCsrException extends RuntimeException {

    public Integer code;

    public LiveCsrException(String msg) {
        super(msg);
    }

    public LiveCsrException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
