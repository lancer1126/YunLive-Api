package com.lance.yunlive.common;

import com.lance.yunlive.common.enums.ResultCode;
import lombok.Data;

@Data
public class ResultEntity {

    private Integer code;
    private Boolean success;
    private String message;
    private Object data;

    public ResultEntity(Integer code, Boolean success, String message, Object data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ResultEntity success(Object data) {
        return buildResult(ResultCode.SUCCESS, true, "success", data);
    }

    public static ResultEntity fail(String message) {
        return buildResult(ResultCode.FAIL, false, message, null);
    }

    public static ResultEntity buildResult(ResultCode resultCode, Boolean success, String message, Object data) {
        return buildResult(resultCode.code, success, message, data);
    }

    private static ResultEntity buildResult(Integer code, Boolean success, String message, Object data) {
        return new ResultEntity(code, success, message, data);
    }
}
