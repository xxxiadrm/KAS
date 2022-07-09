package com.example.kas.exception;

import com.example.result.KasStatus;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 오류 처리를 위한 내부 정의 Exception
 */
@Data
public class HttpStatusException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;

    public HttpStatusException(HttpStatus httpStatus, KasStatus kasStatus) {
        super(kasStatus.getMessage());
        this.setHttpStatus(httpStatus);
        this.setErrorCode(kasStatus.getCode());
        this.setErrorMessage(kasStatus.getMessage());
    }

}