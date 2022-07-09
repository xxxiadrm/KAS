package com.example.kas.exception;

import com.example.result.ResultMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 오류 처리 controller
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<ResultMessage> httpStatusExceptionHandler(HttpStatusException e) {
        // 에러 내용 설정
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(e.getErrorCode());
        resultMessage.setDesc(e.getErrorMessage());
        return new ResponseEntity<>(resultMessage, e.getHttpStatus());
    }
}