package com.lance.yunlive.common.exception;

public class LiveException extends RuntimeException {

    public Integer code;

    public LiveException(String msg) {
        super(msg);
    }

    public LiveException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
