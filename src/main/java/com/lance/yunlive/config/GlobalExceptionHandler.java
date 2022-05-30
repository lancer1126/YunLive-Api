package com.lance.yunlive.config;

import com.lance.yunlive.common.ResultEntity;
import com.lance.yunlive.common.enums.ResultCode;
import com.lance.yunlive.common.exception.LiveCsrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResultEntity handleException(LiveCsrException ex) {
        Integer code = ResultCode.FAIL.code;
        if (ex.code != null) {
            code = ex.code;
        }
        return new ResultEntity(code, false, ex.getMessage(), null);
    }
}
