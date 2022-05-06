package com.lance.yunlive.common;

import com.lance.yunlive.common.enums.ResultCode;
import lombok.Data;

@Data
public class ResultEntity {
    private int code;
    private String message;
    private Object data;

    public ResultEntity(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultEntity success(Object data) {
        return buildResult(ResultCode.SUCCESS, "success", data);
    }

    public static ResultEntity fail(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    public static ResultEntity buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    private static ResultEntity buildResult(int code, String message, Object data) {
        return new ResultEntity(code, message, data);
    }
}
