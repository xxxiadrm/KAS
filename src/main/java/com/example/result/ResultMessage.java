package com.example.result;

import lombok.Data;
/**
 * 처리 결과 객체
 */
@Data
public class ResultMessage {
    private String status;
    private String desc;
    private Object result;
}